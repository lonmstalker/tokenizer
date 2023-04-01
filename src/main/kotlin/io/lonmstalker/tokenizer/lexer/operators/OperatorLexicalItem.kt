package io.lonmstalker.tokenizer.lexer.operators

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.enums.Tokens
import io.lonmstalker.tokenizer.lexer.LexicalItem
import java.util.function.Predicate

class OperatorLexicalItem(
    val type: Tokens.Operators,
    private val isSupportFunction: Predicate<OperatorLexicalItem>
) : LexicalItem() {

    override fun getLexicalType(): LexicalType = LexicalType.OPERATION

    override fun isSupport(): Boolean = this.isSupportFunction.test(this)

    override fun getName(): String = this.type.operation
}