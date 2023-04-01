package io.lonmstalker.tokenizer.lexer.identifier

import io.lonmstalker.tokenizer.enums.LexicalType
import io.lonmstalker.tokenizer.lexer.LexicalItem
import java.util.function.Predicate

class IdentifierLexicalItem(
    val id: String,
    private val isSupportFunction: Predicate<IdentifierLexicalItem>
) : LexicalItem() {
    override fun getLexicalType(): LexicalType = LexicalType.IDENTIFIER
    override fun isSupport(): Boolean = true
    override fun getName(): String = "identifier"
}