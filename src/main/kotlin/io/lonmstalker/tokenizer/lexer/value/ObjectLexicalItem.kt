package io.lonmstalker.tokenizer.lexer.value

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.lexer.LexicalItem
import io.lonmstalker.tokenizer.lexer.LexicalToken

/**
 * Text holder: Any()
 */
class ObjectLexicalItem(val value: Any?) : LexicalItem() {

    override fun getLexicalType(): LexicalType = LexicalType.VALUE

    override fun isSupport(): Boolean =
        super.bothTokensEqualLexicalType(LexicalType.OPERATION)
}