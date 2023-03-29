package io.lonmstalker.tokenizer.enums

enum class Tokens(val symbol: Char) {
    START_BLOCK('{'),
    START_ARG('('),
    END_ARG(')'),
    END_BLOCK('}'),
    ;
}