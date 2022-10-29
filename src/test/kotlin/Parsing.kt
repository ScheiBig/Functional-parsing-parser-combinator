@file:Suppress("ClassName")

import kotlin.test.*

/*
 TODO:
   - test rest of primitives
   - create tests for advanced scenarios
 */

class `Parsing Operations Tests` {
    
    @Test
    fun `test parsing results`() {
        val parser = { inp: String -> if (inp.isEmpty()) null else inp.first() without inp.substring(1) }
        
        assertEquals(
            'a' without "bc",
            parse(parser, "abc")
        )
    }
    
    @Test
    fun `test parser as functor`() {
        assertEquals(
            'A' without "bc",
            Parser.item map Char::uppercaseChar parse "abc"
        )
        
        assertEquals(
            1 without "23",
            Parser.item map Char::digitToInt parse "123"
        )
        
        assertEquals(
            0x61 without "bc",
            Parser.item map Char::code parse "abc"
        )
    }
    
    @Test
    fun `test parser as applicative`() {
        val functionParser = Parser.pure({ x: Char, _: Char, z: Char -> x to z }.curry)
        val resultingParser = ((functionParser apply Parser.item) apply Parser.item) apply Parser.item
        
        assertEquals(
            ('a' to 'c') without "def",
            resultingParser parse "abcdef"
        )
        
        assertEquals(
            null,
            resultingParser parse "ab"
        )
    }
    
    @Test
    fun `test parser as monad`() {
        val parser = Parser.item bind { x ->
            Parser.item follow Parser.item bind { z ->
                Parser.pure(x to z)
            }
        }
        
        assertEquals(
            ('a' to 'c') without "def",
            parser parse "abcdef"
        )
        
        assertEquals(
            null,
            parser parse "ab"
        )
    }
}

class `Parsing Primitives Tests` {
    
    @Test
    fun `test empty parser`() {
        assertEquals(
            null,
            Parser.empty<Nothing>() parse "abc"
        )
    }
    
    @Test
    fun `test pure parser`() {
        assertEquals(
            1 without "abc",
            Parser.pure(1) parse "abc"
        )
    }
    
    @Test
    fun `test item parser`() {
        assertEquals(
            'a' without "bc",
            Parser.item parse "abc"
        )
        
        assertEquals(
            null,
            Parser.item parse ""
        )
    }
    
    @Test
    fun `test some multiplier`() {
        assertEquals(
            null,
            Parser.item.some() parse ""
        )
        
        assertEquals(
            "abc" without "",
            Parser.item.some() parse "abc"
        )
    }
    
    @Test
    fun `test many multiplier`() {
        assertEquals(
            "" without "",
            Parser.item.many() parse ""
        )
        
        assertEquals(
            "abc" without "",
            Parser.item.many() parse "abc"
        )
    }
    
    @Test
    fun `test string parser`() {
        assertEquals(
            "123" without "abc",
            Parser.string("123") parse "123abc"
        )
    }
}
