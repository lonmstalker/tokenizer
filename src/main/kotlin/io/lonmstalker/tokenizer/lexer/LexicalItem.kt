package io.lonmstalker.tokenizer.lexer

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.enums.Types
import io.lonmstalker.tokenizer.utils.CompileUtils.compileError

/**
 * Action tree
 */
abstract class LexicalItem(
    private var line: Int = 0,
    private var endItemIndex: Int = 0,
    private var startItemIndex: Int = 0,
    var prevToken: LexicalItem? = null,
    var nextToken: LexicalItem? = null
) : LexicalToken {

    @Throws(UnsupportedOperationException::class)
    override fun getValueType(): Types {
        throw UnsupportedOperationException("no value or call item")
    }

    override fun add(prevToken: LexicalItem?, nextToken: LexicalItem?) {
        this.prevToken = prevToken
        this.nextToken = nextToken
    }

    override fun setIndex(line: Int, startIndex: Int, endIndex: Int) {
        this.line = line
        this.endItemIndex = endIndex
        this.startItemIndex = startIndex
    }

    override fun invalidateToken(message: String) {
        compileError(line, startItemIndex, endItemIndex, message)
    }

    fun isValueTokens() = this.isValueType(prevToken) && this.isValueType(nextToken)

    @Throws(UnsupportedOperationException::class)
    fun isEqualTypes() = prevToken?.getValueType() == nextToken?.getValueType()

    @Throws(UnsupportedOperationException::class)
    fun isAtLeastOneString(): Boolean =
        prevToken?.getValueType() == Types.STRING || nextToken?.getValueType() == Types.STRING

    fun bothTokensEqualLexicalType(type: LexicalType) =
        prevToken?.getLexicalType() == type && nextToken?.getLexicalType() == type


    private fun isValueType(item: LexicalItem?): Boolean {
        val lexicalType = item?.getLexicalType()
        return lexicalType == LexicalType.CALL
                || lexicalType == LexicalType.VALUE
                || lexicalType == LexicalType.IDENTIFIER
    }

}