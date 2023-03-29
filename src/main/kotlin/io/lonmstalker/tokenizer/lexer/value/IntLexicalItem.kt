package io.lonmstalker.tokenizer.lexer.value

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.lexer.LexicalItem

/**
 * Integer holder: 1
 */
class IntLexicalItem(val value: Int?) : LexicalItem() {

    override fun getLexicalType(): LexicalType = LexicalType.VALUE

    override fun isSupport(): Boolean =
        super.bothTokensEqualLexicalType(LexicalType.OPERATION)
}