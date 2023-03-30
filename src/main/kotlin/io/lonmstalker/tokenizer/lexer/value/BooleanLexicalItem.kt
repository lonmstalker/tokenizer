package io.lonmstalker.tokenizer.lexer.value

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.lexer.LexicalItem
import io.lonmstalker.tokenizer.lexer.LexicalToken

/**
 * Text holder: false
 */
class BooleanLexicalItem(
    val value: Boolean?,
) : LexicalItem() {

    override fun getLexicalType(): LexicalType = LexicalType.VALUE

    override fun isSupport(): Boolean =
        super.bothTokensEqualLexicalType(LexicalType.OPERATION)

    override fun getName(): String = Boolean::class.java.simpleName
}