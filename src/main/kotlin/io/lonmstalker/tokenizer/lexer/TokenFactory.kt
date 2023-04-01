package io.lonmstalker.tokenizer.lexer

import io.lonmstalker.tokenizer.enums.Tokens
import io.lonmstalker.tokenizer.lexer.identifier.CallLexicalItem
import io.lonmstalker.tokenizer.lexer.identifier.IdentifierLexicalItem
import io.lonmstalker.tokenizer.lexer.identifier.NewLineLexicalItem
import io.lonmstalker.tokenizer.lexer.operators.FlowLexicalItem
import io.lonmstalker.tokenizer.lexer.operators.KeywordLexicalItem
import io.lonmstalker.tokenizer.lexer.operators.OperationsLexicalItem
import io.lonmstalker.tokenizer.lexer.operators.OperatorLexicalItem
import io.lonmstalker.tokenizer.lexer.validators.*
import io.lonmstalker.tokenizer.lexer.value.ValueLexicalItem
import io.netty.util.internal.StringUtil.COMMA
import io.netty.util.internal.StringUtil.DOUBLE_QUOTE

class TokenFactory {

    fun findToken(token: Char, lexerContext: LexerContext): LexicalItem =
        this.resolveToken(token)
            .apply { setIndex(this, lexerContext) }

    fun findToken(token: CharArray, lexerContext: LexerContext): LexicalItem =
        this.resolveToken(token, lexerContext)
            .apply { setIndex(this, lexerContext) }

    private fun setIndex(lexicalItem: LexicalItem, lexerContext: LexerContext) {
        lexicalItem.setIndex(lexerContext.currentLine, lexerContext.startIndex, lexerContext.endIndex)
    }

    private fun resolveToken(token: CharArray, lexerContext: LexerContext): LexicalItem {
        var isDigit = true
        var isString = true

        var funcNameIndex = 0
        var isCallFunc = false

        for ((index, symbol) in token.withIndex()) {
            if (RESERVED_SYMBOLS.contains(symbol)) {
                error("symbol $symbol can used only like operator")
            }

            if (!symbol.isDigit()) {
                isDigit = false
            }

            if (!symbol.isLetterOrDigit() && symbol != Tokens.Flow.DOUBLE_QUOTE.symbol) {
                isString = false
            }

            if (!isCallFunc && symbol == Tokens.Flow.START_ARG.symbol) {
                isCallFunc = true
                funcNameIndex = index - 1
            }
        }

        val value = String(token)
        if (isDigit) {
            return ValueLexicalItem(value.toInt(), Tokens.Types.NUMBER, NumberValidator())
        }

        if (isString) {
            val keyword = Tokens.Keywords.values().firstOrNull { it.word == value }
            if (keyword != null) {
                return KeywordLexicalItem(keyword, KeywordValidator())
            }
        }

        val op = Tokens.Operations.values().firstOrNull { it.symbol == value }
        if (op != null) {
            return OperationsLexicalItem(op, OperationsValidator())
        }

        if (isString) {
            val firstSymbol = value[0]
            if (firstSymbol.isDigit()) {
                error("string $value can't start from number")
            }
            if (firstSymbol == Tokens.Flow.DOUBLE_QUOTE.symbol) {
                return ValueLexicalItem(value.substring(value.indices), Tokens.Types.STRING, StringValidator())
            }
            return IdentifierLexicalItem(value, IdentifierValidator())
        }

        if (isCallFunc) {
            val name = value.substring(0..funcNameIndex)
            val args = value
                .substring(funcNameIndex + 2 until value.length)
                .split(COMMA)
                .map { this.resolveToken(it.toCharArray(), lexerContext) }
            return CallLexicalItem(name, args)
        }

        this.tokenNotSupported(String(token))
    }

    private fun resolveToken(token: Char): LexicalItem {
        if (token.isDigit()) {
            return ValueLexicalItem(token.digitToInt(), Tokens.Types.NUMBER, NumberValidator())
        }
        if (token.isWhitespace()) {
            return NewLineLexicalItem()
        }

        val op = Tokens.Operators.values().firstOrNull { it.symbol == token }
        if (op != null) {
            return OperatorLexicalItem(op, OperatorValidator())
        }

        val flow = Tokens.Flow.values().firstOrNull { it.symbol == token }
        if (flow != null) {
            return FlowLexicalItem(flow, FlowLexicalValidator())
        }

        this.tokenNotSupported(token)
    }

    private fun tokenNotSupported(token: Any): Nothing = error("token $token not known")

    companion object {
        @JvmStatic
        private val RESERVED_WORDS = setOf<String>()

        @JvmStatic
        private val RESERVED_SYMBOLS = setOf<Char>()
    }
}