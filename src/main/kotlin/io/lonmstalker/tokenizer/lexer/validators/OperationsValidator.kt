package io.lonmstalker.tokenizer.lexer.validators

import io.lonmstalker.tokenizer.lexer.operators.OperationsLexicalItem
import java.util.function.Predicate

class OperationsValidator : Predicate<OperationsLexicalItem> {
    override fun test(t: OperationsLexicalItem): Boolean {
        return true // todo: add realization
    }
}