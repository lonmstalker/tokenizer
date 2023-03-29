package io.lonmstalker.tokenizer.lexer.operation

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.lexer.LexicalItem
import io.lonmstalker.tokenizer.lexer.LexicalToken

/**
 * Operation division: 1 / 1
 */
class DivisionLexicalItem(
    prevToken: LexicalItem,
    nextToken: LexicalItem
) : LexicalItem(prevToken, nextToken) {

    override fun getLexicalType(): LexicalType = LexicalType.OPERATION

    override fun isSupport(prevToken: LexicalToken, nextToken: LexicalToken): Boolean =
        super.isValueTokens() && super.isEqualTypes()
}