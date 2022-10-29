import java.io.File
import java.io.IOException
import java.io.PrintWriter

@Throws(IOException::class)
fun main() {
    val file = File("src/main/kotlin/FOP.kt")
    file.createNewFile()
    
    val wr = file.printWriter().also {
        it.println(
            """
            /*
             * This file is generated using FunctionOperatorsGeneratorKt.main()
             */
             
            @file:Suppress("unused", "KDocMissingDocumentation")
        """.trimIndent()
        )
        it.println()
    }
    
    generateCurryUncurry('a'..'p', wr)
    
    wr.close()
}


/**
 * Generates definitions of curry and uncurry properties on function objects
 * and writes them to file attached to [writer]
 */
fun generateCurryUncurry(charRange: CharRange, writer: PrintWriter) {
    
    require(charRange.count() > 1) { "Cannot create currying for function with one parameter" }
    
    val prLn: (String) -> Unit = writer::println
    
    for (c in charRange.drop(1)) {
        val inParams = charRange.first..c
        val inParamsUpp = inParams.map(Char::uppercaseChar)
        
        val genericParameters = (inParamsUpp + 'R').joinToString(
            separator = ", ",
            prefix = "<",
            postfix = ">"
        )
        
        val uncurriedType = inParamsUpp.joinToString(
            separator = ", ",
            prefix = "(",
            postfix = ") -> R"
        )
        
        val curriedType = (inParamsUpp).joinToString(
            separator = ") -> (",
            prefix = "(",
            postfix = ") -> R"
        )
        
        val curryGetterBody = inParams.joinToString(
            separator = "",
            postfix = "this(${inParams.joinToString()})"
        ) { "fun($it: ${it.uppercaseChar()}) = " }
        
        val uncurryGetterBody = inParams.joinToString(
            separator = ", ",
            prefix = "fun(",
            postfix = ") = "
        ) { "$it: ${it.uppercaseChar()}" } + inParams.joinToString(
            separator = ")(",
            prefix = "this(",
            postfix = ")"
        )
        
        prLn("val $genericParameters ($uncurriedType).curry: $curriedType")
        prLn("    get() = $curryGetterBody")
        prLn("")
        prLn("val $genericParameters ($curriedType).curry: $uncurriedType")
        prLn("    get() = $uncurryGetterBody")
        prLn("")
        prLn("")
    }
}