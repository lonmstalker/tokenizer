package io.lonmstalker.tokenizer.lexer.identifier

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.lexer.LexicalItem
import io.lonmstalker.tokenizer.lexer.LexicalToken

class CallLexicalItem(
    val id: String,
    val args: Array<LexicalItem>
) : LexicalItem() {
    override fun getLexicalType(): LexicalType = LexicalType.CALL
    override fun isSupport(prevToken: LexicalToken, nextToken: LexicalToken): Boolean = false
}