package io.lonmstalker.tokenizer.lexer

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.enums.Types
import io.lonmstalker.tokenizer.exception.CompileException

/**
 * Action tree
 */
abstract class LexicalItem(
    private val line: Int,
    private val startItemIndex: Int,
    private val endItemIndex: Int,
    private var prevToken: LexicalItem? = null,
    private var nextToken: LexicalItem? = null
) : LexicalToken {

    @Throws(UnsupportedOperationException::class)
    override fun getValueType(): Types {
        throw UnsupportedOperationException("no value or call item")
    }

    override fun add(prevToken: LexicalItem?, nextToken: LexicalItem?) {
        this.prevToken = prevToken
        this.nextToken = nextToken
    }

    override fun invalidateToken(message: String) {
        throw CompileException("line: $line, index: $startItemIndex-$endItemIndex, message: $message")
    }

    fun isValueTokens() = this.isValueType(prevToken) && this.isValueType(nextToken)

    @Throws(UnsupportedOperationException::class)
    fun isEqualTypes() = prevToken?.getValueType() == nextToken?.getValueType()

    @Throws(UnsupportedOperationException::class)
    fun isAtLeastOneString(): Boolean =
        prevToken?.getValueType() == Types.STRING
                || nextToken?.getValueType() == Types.STRING

    fun bothTokensEqualLexicalType(type: LexicalType) =
        prevToken?.getLexicalType() == type && nextToken?.getLexicalType() == type


    private fun isValueType(item: LexicalItem?): Boolean {
        val lexicalType = item?.getLexicalType()
        return lexicalType == LexicalType.CALL
                || lexicalType == LexicalType.VALUE
                || lexicalType == LexicalType.IDENTIFIER
    }

}