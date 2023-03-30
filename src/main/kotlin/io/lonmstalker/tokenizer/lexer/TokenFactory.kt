package io.lonmstalker.tokenizer.lexer

import io.lonmstalker.tokenizer.enums.Keywords
import io.lonmstalker.tokenizer.enums.Types

class TokenFactory {

    fun findToken(token: Char, lexerContext: LexerContext): LexicalItem {
    }

    fun findToken(token: String, lexerContext: LexerContext): LexicalItem {
    }

    companion object {
        @JvmStatic
        private val RESERVED_TOKENS = HashSet(Keywords.values().map { it.word } + Types.values().map { it.word })
    }
}