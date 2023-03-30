package io.lonmstalker.tokenizer.lexer

import io.lonmstalker.tokenizer.enums.Keywords
import io.lonmstalker.tokenizer.enums.Types
import java.util.function.Supplier

class TokenFactory {

    fun findToken(token: Char, lexerContext: LexerContext): LexicalItem =
        OPERATION_MAP[token]
            ?.get()
            ?.apply { setIndex(this, lexerContext) }
            ?: this.tokenNotSupported(token)

    fun findToken(token: String, lexerContext: LexerContext): LexicalItem =
        LEXICAL_MAP[token]
            ?.get()
            ?.apply { setIndex(this, lexerContext) }
            ?: this.tokenNotSupported(token)

    private fun setIndex(lexicalItem: LexicalItem, lexerContext: LexerContext) {
        lexicalItem.setIndex(lexerContext.currentLine, lexerContext.startIndex, lexerContext.endIndex)
    }

    private fun tokenNotSupported(token: Any): Nothing = error("token $token not known")

    companion object {
        @JvmStatic
        private val OPERATION_MAP = mutableMapOf<Char, Supplier<LexicalItem>>()

        @JvmStatic
        private val LEXICAL_MAP = mutableMapOf<String, Supplier<LexicalItem>>()

        @JvmStatic
        private val RESERVED_TOKENS = HashSet(Keywords.values().map { it.word } + Types.values().map { it.word })
    }
}