package io.lonmstalker.tokenizer.lexer.identifier

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.lexer.LexicalItem

class CallLexicalItem(
    val id: String,
    val args: List<LexicalItem>,
) : LexicalItem() {
    override fun getLexicalType(): LexicalType = LexicalType.CALL
    override fun isSupport(): Boolean = false
    override fun getName(): String = "Function call"
}