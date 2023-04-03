package io.lonmstalker.tokenizer.lexer

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.enums.Tokens
import io.lonmstalker.tokenizer.utils.compileError
import io.lonmstalker.tokenizer.utils.getLineMessage

/**
 * Action tree
 */
abstract class LexicalItem(
    private var line: Int = 0,
    private var endIndex: Int = 0,
    private var startIndex: Int = 0,
    var prevToken: LexicalItem? = null,
    var nextToken: LexicalItem? = null
) : LexicalToken {

    @Throws(UnsupportedOperationException::class)
    override fun getValueType(): Tokens.Types {
        throw UnsupportedOperationException("no value or call item")
    }

    override fun add(prevToken: LexicalItem?, nextToken: LexicalItem?) {
        this.prevToken = prevToken
        this.nextToken = nextToken
    }

    override fun setIndex(line: Int, startIndex: Int, endIndex: Int) {
        this.line = line
        this.endIndex = endIndex
        this.startIndex = startIndex
    }

    override fun invalidateToken(message: String) {
        compileError(line, startIndex, endIndex, message)
    }

    fun isValueTokens() = this.isValueType(prevToken) && this.isValueType(nextToken)

    @Throws(UnsupportedOperationException::class)
    fun isEqualTypes() = prevToken?.getValueType() == nextToken?.getValueType()

    @Throws(UnsupportedOperationException::class)
    fun isAtLeastOneString(): Boolean =
        prevToken?.getValueType() == Tokens.Types.STRING || nextToken?.getValueType() == Tokens.Types.STRING

    fun bothTokensEqualLexicalType(type: LexicalType) =
        prevToken?.getLexicalType() == type && nextToken?.getLexicalType() == type

    internal fun getLineMessage() = getLineMessage(line, startIndex, endIndex)

    private fun isValueType(item: LexicalItem?): Boolean {
        val lexicalType = item?.getLexicalType()
        return lexicalType == LexicalType.CALL
                || lexicalType == LexicalType.VALUE
                || lexicalType == LexicalType.IDENTIFIER
    }

}