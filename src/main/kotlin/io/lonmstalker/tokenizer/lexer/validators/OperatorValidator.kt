package io.lonmstalker.tokenizer.lexer.validators

import io.lonmstalker.tokenizer.lexer.operators.OperatorLexicalItem
import java.util.function.Predicate

class OperatorValidator : Predicate<OperatorLexicalItem> {
    override fun test(t: OperatorLexicalItem): Boolean {
        return true // todo: add realization
    }
}