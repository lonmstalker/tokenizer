package io.lonmstalker.tokenizer.lexer.operation

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.lexer.LexicalItem
import io.lonmstalker.tokenizer.lexer.LexicalToken

/**
 * Operation assign: var a = 1
 */
class AssignLexicalItem() : LexicalItem() {

    override fun getLexicalType(): LexicalType = LexicalType.OPERATION

    override fun isSupport(): Boolean =
        super.isValueTokens() && super.isEqualTypes()
}