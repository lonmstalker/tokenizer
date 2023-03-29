package io.lonmstalker.tokenizer.lexer.identifier

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.lexer.LexicalItem
import io.lonmstalker.tokenizer.lexer.LexicalToken

class IdentifierLexicalItem(val id: String) : LexicalItem() {
    override fun getLexicalType(): LexicalType = LexicalType.IDENTIFIER
    override fun isSupport(prevToken: LexicalToken, nextToken: LexicalToken): Boolean = false
}