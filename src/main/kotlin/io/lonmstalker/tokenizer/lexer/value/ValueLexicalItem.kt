package io.lonmstalker.tokenizer.lexer.value

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.enums.Tokens
import io.lonmstalker.tokenizer.lexer.LexicalItem
import java.util.function.Predicate

class ValueLexicalItem(
    val value: Any?,
    val type: Tokens.Types,
    private val isSupportFunction: Predicate<ValueLexicalItem>
) : LexicalItem() {

    override fun getLexicalType(): LexicalType = LexicalType.VALUE

    override fun isSupport(): Boolean = this.isSupportFunction.test(this)

    override fun getName(): String = this.type.word
}