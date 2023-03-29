package io.lonmstalker.tokenizer.front

import io.lonmstalker.tokenizer.enums.Types

data class FunctionContext(
    val id: String,
    val returnType: String,
    val args: Map<String, Types>
)