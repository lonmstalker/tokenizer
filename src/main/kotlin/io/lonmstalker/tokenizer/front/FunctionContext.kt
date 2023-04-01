package io.lonmstalker.tokenizer.front

import io.lonmstalker.tokenizer.enums.Tokens

data class FunctionContext(
    val id: String,
    val returnType: String,
    val args: Map<String, Tokens.Types>
)