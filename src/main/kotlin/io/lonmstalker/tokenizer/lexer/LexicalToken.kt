package io.lonmstalker.tokenizer.lexer

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.enums.Tokens

interface LexicalToken {
    fun getLexicalType(): LexicalType
    fun getValueType(): Tokens.Types
    fun add(prevToken: LexicalItem?, nextToken: LexicalItem?)
    fun isSupport(): Boolean
    fun invalidateToken(message: String)
    fun getName(): String
    fun setIndex(line: Int, startIndex: Int, endIndex: Int)
}