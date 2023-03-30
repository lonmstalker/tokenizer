package io.lonmstalker.tokenizer.lexer.identifier

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.lexer.LexicalItem
import io.lonmstalker.tokenizer.lexer.LexicalToken

class NewLineLexicalItem : LexicalItem() {
    override fun getLexicalType(): LexicalType = LexicalType.NEW_LINE
    override fun isSupport(): Boolean = false
    override fun getName(): String = "new line"
}