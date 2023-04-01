package io.lonmstalker.tokenizer.lexer.validators

import io.lonmstalker.tokenizer.lexer.identifier.IdentifierLexicalItem
import io.lonmstalker.tokenizer.lexer.value.ValueLexicalItem
import java.util.function.Predicate

class IdentifierValidator : Predicate<IdentifierLexicalItem> {
    override fun test(t: IdentifierLexicalItem): Boolean {
        TODO("Not yet implemented")
    }
}