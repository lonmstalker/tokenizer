package io.lonmstalker.tokenizer.lexer.operators

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.enums.Tokens
import io.lonmstalker.tokenizer.lexer.LexicalItem
import java.util.function.Predicate

class FlowLexicalItem(
    val type: Tokens.Flow,
    private val isSupportFunction: Predicate<FlowLexicalItem>
) : LexicalItem() {

    override fun getLexicalType(): LexicalType = LexicalType.FLOW

    override fun isSupport(): Boolean = this.isSupportFunction.test(this)

    override fun getName(): String = this.type.name
}