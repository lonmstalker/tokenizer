package io.lonmstalker.tokenizer.lexer

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.enums.Types

interface LexicalToken {
    fun getLexicalType(): LexicalType
    fun getValueType(): Types
    fun add(prevToken: LexicalItem?, nextToken: LexicalItem?)
    fun isSupport(): Boolean
    fun invalidateToken(message: String)
}