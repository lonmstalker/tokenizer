package io.lonmstalker.tokenizer.lexer.value

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.front.CustomType
import io.lonmstalker.tokenizer.lexer.LexicalItem
import io.lonmstalker.tokenizer.lexer.LexicalToken

/**
 * Text holder: Any()
 */
class ObjectLexicalItem(
    val value: Any?,
    val type: CustomType,
) : LexicalItem() {

    override fun getLexicalType(): LexicalType = LexicalType.VALUE

    override fun isSupport(): Boolean =
        super.bothTokensEqualLexicalType(LexicalType.OPERATION)

    override fun getName(): String = type.name
}