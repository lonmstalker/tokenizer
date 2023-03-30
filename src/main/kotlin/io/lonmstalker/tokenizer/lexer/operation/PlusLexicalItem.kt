package io.lonmstalker.tokenizer.lexer.operation

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.lexer.LexicalItem
import io.lonmstalker.tokenizer.lexer.LexicalToken

/**
 * Operation add: 1 + 1
 */
class PlusLexicalItem : LexicalItem() {

    override fun getLexicalType(): LexicalType = LexicalType.OPERATION

    override fun isSupport(): Boolean =
        super.isValueTokens() && (super.isEqualTypes() || super.isAtLeastOneString())

    override fun getName(): String = "plus"
}