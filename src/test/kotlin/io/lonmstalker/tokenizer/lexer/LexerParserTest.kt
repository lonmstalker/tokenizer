package io.lonmstalker.tokenizer.lexer

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.front.FunctionContext
import io.lonmstalker.tokenizer.lexer.identifier.CallLexicalItem
import io.lonmstalker.tokenizer.lexer.identifier.IdentifierLexicalItem
import io.lonmstalker.tokenizer.lexer.value.ValueLexicalItem
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class LexerParserTest {

    private val lexerParser = LexerParser()

    @Test
    fun `should ok parse usually code`() {
        // given:
        val code = """
            let one = 12
            let two = 12 + 3
            let result = one + two
        """.trimIndent()

        // when:
        val ctx = FunctionContext("", "", mapOf())
        val res = assertDoesNotThrow { lexerParser.parse(code, ctx, 1) }

        // then:
        assertNotNull(res)
        assertNotNull(res!!.nextToken)
        assertEquals(LexicalType.IDENTIFIER, res.nextToken!!.getLexicalType())
        assertEquals("two", (res.nextToken!! as IdentifierLexicalItem).id)
    }

    @Test
    fun `should ok recognize string`() {
        // given:
        val code = """
            let one = "abc"
        """.trimIndent()

        // when:
        val ctx = FunctionContext("", "", mapOf())
        val res = assertDoesNotThrow { lexerParser.parse(code, ctx, 1) }

        // then:
        assertNotNull(res)
        assertNotNull(res!!.nextToken)
        assertEquals(LexicalType.VALUE, res.nextToken!!.getLexicalType())
        assertEquals("abc", (res.nextToken!! as ValueLexicalItem).value)
    }

    @Test
    fun `should ok call func`() {
        // given:
        val code = """
            let one = func("abc")
        """.trimIndent()

        // when:
        val ctx = FunctionContext("", "", mapOf())
        val res = assertDoesNotThrow { lexerParser.parse(code, ctx, 1) }

        // then:
        assertNotNull(res)
        assertNotNull(res!!.nextToken)
        assertEquals(LexicalType.CALL, res.nextToken!!.getLexicalType())
        assertEquals("func", (res.nextToken!! as CallLexicalItem).id)
    }
}