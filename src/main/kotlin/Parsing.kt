/*
 * Functional parsing library based on http://www.cs.nott.ac.uk/~pszgmh/Parsing.hs;
 * code from chapter 13 of Programming in Haskell 2nd edition,
 * by Graham Hutton, Cambridge University Press, 2016.
 */
@file:Suppress("KDocMissingDocumentation", "unused", "MemberVisibilityCanBePrivate")

/**
 * Parses input string
 * @param p Parser function with logic used for parsing
 * @param inp Input string
 * @return [Parsing] object
 */
fun <R> parse(p: (String) -> Parsing<R>?, inp: String) = p(inp)

/**
 * Parses input string
 * @param p Parser object with logic used for parsing
 * @param inp Input string
 * @return [Parsing] object
 */
fun <R> parse(p: Parser<R>, inp: String) = p.parser(inp)

/**
 * Return type of the parsing operation
 * @property result Token of type [R] extracted from the input
 * @property out Unconsumed part of the input
 */
data class Parsing<R>(val result: R, val out: String) {
    override fun toString() = "( \"$result\" , \"$out\" )"
}

/**
 * Creates a new [Parsing] object
 * @return Parsing with `result` of [this] value **`without`** consuming [out] value
 */
infix fun <R> R.without(out: String) = Parsing(this, out)

/**
 * Class that wraps logic used to parse input strings
 * @param R Type of resulting value of parsing using this parser
 * @property parser lambda that consumes [String] and returns [Parsing] on success, and `null` on failure
 */
class Parser<R>(val parser: (String) -> Parsing<R>?) {
    
    /**
     * Parses input string using this parser
     * @param inp Input string
     * @return [Parsing] object
     */
    infix fun parse(inp: String) = parser(inp)
    
    /**
     * Basic and derived primitives of [Parser] objects
     */
    companion object Primitives {
        
        /* Basic primitives */
        
        /**
         * Basic primitive parser that always succeeds with provided [result] as resulting value,
         * without consuming any of the input string
         *
         * ```
         * Parser.pure(1) parse "abc" == 1 without "abc"
         * ```
         */
        fun <V> pure(result: V) = Parser { inp -> result without inp }
        
        /**
         * Basic primitive parser that always fails, without consuming any of the input string
         *
         * ```
         * Parser.empty() parse "abc" == null
         * ```
         */
        fun <N> empty() = Parser<N> { null }
        
        /**
         * Basic primitive parser, which fails on empty `input`
         * and succeeds consuming first character of `input` as resulting value
         *
         * ```
         * Parser.item parse "abc" == 'a' without "bc"
         * ```
         */
        val item = Parser { inp -> if (inp.isEmpty()) null else inp.first() without inp.substring(1) }
        
        
        /* Derived primitives */
        
        /**
         * Derived primitive parser that succeeds consuming first character of `input` as resulting value,
         * if that character satisfies [predicate], and fails otherwise
         *
         * ```
         * Parser.satisfying { it == '#' } parse "#abc" == '#' without "abc"
         * ```
         */
        fun satisfying(predicate: (Char) -> Boolean) = item bind { x ->
            if (predicate(x)) pure(x) else empty()
        }
        
        /**
         * Derived primitive parser that succeeds consuming first character of `input` as resulting value,
         * if that character satisfies is a digit, and fails otherwise
         *
         * ```
         * Parser.digit parse "123abc" == '1' without "23abc"
         * ```
         */
        val digit = satisfying(Char::isDigit)
        
        /**
         * Derived primitive parser that succeeds consuming first character of `input` as resulting value,
         * if that character satisfies is a lowercase letter, and fails otherwise
         *
         * ```
         * Parser.lower parse "aBc" == 'a' without "Bc"
         * ```
         */
        val lower = satisfying(Char::isLowerCase)
        
        /**
         * Derived primitive parser that succeeds consuming first character of `input` as resulting value,
         * if that character satisfies is an uppercase letter, and fails otherwise
         *
         * ```
         * Parser.upper parse "AbC" == 'A' without "bC"
         * ```
         */
        val upper = satisfying(Char::isUpperCase)
        
        /**
         * Derived primitive parser that succeeds consuming first character of `input` as resulting value,
         * if that character satisfies is a letter, and fails otherwise
         *
         * ```
         * Parser.letter parse "abc123" == 'a' without "bc123"
         * ```
         */
        val letter = satisfying(Char::isLetter)
        
        /**
         * Derived primitive parser that succeeds consuming first character of `input` as resulting value,
         * if that character satisfies is a letter or a digit, and fails otherwise
         *
         * ```
         * Parser.letter parse "abc123" == 'a' without "bc123"
         * ```
         */
        val alphaNum = letter alter digit
        
        /**
         * Derived primitive parser that succeeds consuming first character of `input` as resulting value,
         * if that character is equal to [chr], and fails otherwise
         *
         * ```
         * Parser.char('a') parse "abc123" == 'a' without "bc123"
         * ```
         */
        fun char(chr: Char) = satisfying { it == chr }
        
        /**
         * Derived primitive parser that succeeds consuming first [str.length][String.length] of characters of `input`
         * as resulting value, if consumed string is equal to [str], and fails otherwise
         *
         * ```
         * Parser.string("ab") parse "abc123" == "ab" without "c123"
         * ```
         */
        fun string(str: String): Parser<String> =
            if (str.isEmpty()) pure("")
            else char(str.first()) follow string(str.drop(1)) follow pure(str)
        
        /**
         * Derived primitive parser that succeeds consuming characters of `input` as resulting value,
         * as long as they are valid Kotlin identifier (any letter followed by alphanumeric characters),
         * and fails otherwise
         *
         * ```
         * Parser.identifier parse "abc123.hello()" == "abc123" without ".hello()"
         * ```
         */
        val identifier = letter bind { x ->
            alphaNum.many() bind { xs ->
                pure("$x$xs")
            }
        }
        
        /**
         * Derived primitive parser that succeeds consuming characters of `input` as resulting value,
         * as long as they form natural number, and fails otherwise
         *
         * ```
         * Parser.natural parse "123abc" == "123" without "abc"
         * ```
         */
        val natural = digit.some() bind { xs ->
            val number = xs.toIntOrNull()
            if (number == null) empty()
            else pure(number)
        }
        
        /**
         * Derived primitive parser that succeeds consuming characters of `input` as resulting value,
         * as long as they form integer number, and fails otherwise
         *
         * ```
         * Parser.natural parse "-123-abc" == "-123" without "-abc"
         * ```
         */
        val integer = char('-') follow natural bind { n -> pure(-n) } alter natural
        
        
        /* Derived primitives that ignore whitespaces */
        
        
        /**
         * Derived primitive parser that succeeds consuming first character of `input` as resulting value,
         * if that character is a whitespace, and fails otherwise
         *
         * ```
         * Parser.space parse "    abc123" == "    " without "abc123"
         * ```
         */
        val space = satisfying(Char::isWhitespace).many()
        
        /**
         * Derived primitive parser that succeeds consuming characters of `input` as resulting value,
         * as long as they form token specified by [of] parser (token being a string of characters followed
         * and/or led by whitespaces), and fails otherwise
         *
         * ```
         * Parser.token(Parser.letter) parse "  abc  ABC  " == "abc" without "ABC  "
         * ```
         */
        fun <Q> token(of: Parser<Q>) = space follow of bind { v -> space follow pure(v) }
        
        /**
         * Derived primitive parser that succeeds consuming characters of `input` as resulting value,
         * as long as they form identifier token, and fails otherwise
         *
         * @see identifier
         *
         * ```
         * Parser.nextIdentifier parse "  abc123 plus hello()" == "abc123" without "plus hello()"
         * ```
         */
        val nextIdentifier = token(identifier)
        
        /**
         * Derived primitive parser that succeeds consuming characters of `input` as resulting value,
         * as long as they form natural number token, and fails otherwise
         *
         * @see natural
         *
         * ```
         * Parser.nextNatural parse "  123 + 456  " == "123" without " + 456  "
         * ```
         */
        val nextNatural = token(natural)
        
        /**
         * Derived primitive parser that succeeds consuming characters of `input` as resulting value,
         * as long as they form integer number token, and fails otherwise
         *
         * @see integer
         *
         * ```
         * Parser.nextNatural parse "  -123 * 456  " == "-123" without " * 456  "
         * ```
         */
        val nextInteger = token(integer)
        
        /**
         * Derived primitive parser that succeeds consuming characters of `input` as resulting value,
         * as long as they form symbol token (symbol being any string), and fails otherwise
         *
         * @see string
         *
         * ```
         * Parser.nextSymbol("amo") parse " amo gus " == "amo" without "gus "
         * ```
         */
        fun nextSymbol(symbol: String) = token(string(symbol))
    }
}


/**
 * `apply` operation takes function resulting from parsing `input` using [this] parser,
 * [map][Parser.map]`'s` resulting function to [modified] parser.
 * This newly created parser is used to get resulting parsing from the rest of input
 * left after parsing with [this] parser.
 *
 * It allows to treat [Parser] as **`Applicative`** `(operator <*>)`
 *
 * Application can be used instead of [bind] and [follow]:
 * ```
 * import Parser.Primitives.pure as pureP;
 * import Parser.Primitives.item as itemP;
 * val a = pureP { x: Char -> { _: Char -> { z: Char -> x to z } } } apply itemP apply itemP apply itemP
 * // is equivalent to:
 * val b = itemP bind { x -> itemP follow itemP bind { z -> pureP(x to z) } }
 * ```
 */
infix fun <R, Q> Parser<(R) -> Q>.apply(modified: Parser<R>) = Parser { inp ->
    val parsing = this parse inp
    if (parsing == null) null
    else modified map parsing.result parse parsing.out
}

/**
 * `map` operation applies [modifier] `function` to result value of [Parser] if one succeeds,
 * and propagates the failure otherwise.
 *
 * It allows to treat [Parser] as **`Functor`** `(operator <$>)`
 */
infix fun <R, Q> Parser<R>.map(modifier: (R) -> Q) = Parser { inp ->
    val parsing = this parse inp
    if (parsing == null) null
    else modifier(parsing.result) without parsing.out
}

/**
 * `bind` operation composes two parsers sequentially. It parses `input` using [this] parser, and on success creates
 * new parser by applying [transformation] on the result value of parsing, which is used to parse the rest of input
 * left after parsing with [this] parser.
 *
 * It allows to treat [Parser] as **`Monad`** `(operator >>=)`
 *
 * Binding and [following][follow] can be used instead of [apply]:
 * ```
 * import Parser.Primitives.pure as pureP;
 * import Parser.Primitives.item as itemP;
 * val b = itemP bind { x -> itemP follow itemP bind { z -> pureP(x to z) } }
 * // is equivalent to:
 * val a = pureP { x: Char -> { _: Char -> { z: Char -> x to z } } } apply itemP apply itemP apply itemP
 * ```
 */
infix fun <R, Q> Parser<R>.bind(transformation: (R) -> Parser<Q>) = Parser { inp ->
    val parsing = this parse inp
    if (parsing == null) null
    else transformation(parsing.result) parse parsing.out
}


/**
 * `follow` operation composes two parsers sequentially, discarding produced value. It parses `input` using [this] parser,
 * and on success creates new parser, which uses [action] to parse the rest of input left after
 * parsing with [this] parser.
 *
 * It allows to treat [Parser] as `imperative` **`Monad`** `(operator >>)`
 *
 * Following and [binding][bind] can be used instead of [apply]:
 * ```
 * import Parser.Primitives.pure as pureP;
 * import Parser.Primitives.item as itemP;
 * val b = itemP bind { x -> itemP follow itemP bind { z -> pureP(x to z) } }
 * // is equivalent to:
 * val a = pureP { x: Char -> { _: Char -> { z: Char -> x to z } } } apply itemP apply itemP apply itemP
 * ```
 */
infix fun <R, Q> Parser<R>.follow(action: Parser<Q>) = Parser { inp ->
    val parsing = this parse inp
    if (parsing == null) null
    else action parse parsing.out
}

/**
 * `alter` operation provides [alternative] parsing logic to [this] parser, meaning if using [this] parser on `input`
 * fails, [alternative] parser is used to process `input` (which can of course, fail as well)
 *
 * It allows to treat [Parser] as **`Alternative`** `(operator <|>)`
 */
infix fun <R> Parser<R>.alter(alternative: Parser<R>) = Parser { inp ->
    (this parse inp) ?: (alternative parse inp)
}

/**
 * Could be used for recursive declaration of [some] and [many], but not for now
 */
private fun <A, B, C> liftA2(function: (A) -> (B) -> C, action1: Parser<A>, action2: Parser<B>) =
    action1.map(function).apply(action2)

/**
 * `some` multiplier creates new parser, that if successful, results in string of one or more characters, that were
 * consumed from input using logic from [this] parser
 *
 * ```
 * Parser.letter.some() parse "abc123" == "abc" without "123"
 * Parser.letter.some() parse "123abc" == null
 * ```
 */
fun Parser<Char>.some(): Parser<String> = Parser { inp ->
    fun Parser<Char>.furtherParse(lastParsing: Parsing<String>): Parsing<String>? {
        val parsing = this parse lastParsing.out
        return if (parsing == null) null
        else {
            val newParsing = lastParsing.copy(
                result = lastParsing.result + parsing.result,
                out = parsing.out
            )
            this.furtherParse(newParsing) ?: newParsing
        }
    }
    
    val parsing = this parse inp
    if (parsing == null) null
    else {
        val properParsing = parsing.result.toString() without parsing.out
        furtherParse(properParsing) ?: properParsing
    }
}

/**
 * `some` multiplier creates new parser, that if successful, results in string of characters, that were
 * consumed from input using logic from [this] parser, and on failure results in empty string
 *
 * ```
 * Parser.letter.many() parse "abc123" == "abc" without "123"
 * Parser.letter.many() parse "123abc" == "" without "123abc"
 * ```
 */
fun Parser<Char>.many(): Parser<String> = this.some() alter Parser.pure("")
