package io.lonmstalker.tokenizer.lexer.validators

import io.lonmstalker.tokenizer.lexer.operators.KeywordLexicalItem
import java.util.function.Predicate

class KeywordValidator : Predicate<KeywordLexicalItem> {
    override fun test(t: KeywordLexicalItem): Boolean {
        return true // todo: add realization
    }
}