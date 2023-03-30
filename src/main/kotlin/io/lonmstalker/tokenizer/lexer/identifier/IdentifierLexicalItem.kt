package io.lonmstalker.tokenizer.lexer.identifier

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.lexer.LexicalItem

class IdentifierLexicalItem(
    val id: String,
    val isFunc: Boolean
) : LexicalItem() {
    override fun getLexicalType(): LexicalType = LexicalType.IDENTIFIER
    override fun isSupport(): Boolean = false
    override fun getName(): String = "identifier"
}