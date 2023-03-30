package io.lonmstalker.tokenizer.lexer

import io.lonmstalker.tokenizer.front.FunctionContext

data class LexerContext(
    val functionContext: FunctionContext,

    var isStart: Boolean = false,
    var nextLine: Boolean = false,

    var endNextItem: Boolean = false,
    var startNextItem: Boolean = true,

    var currentLine: Int,
    var endIndex: Int = 0,
    var startIndex: Int = 0,

    var currentItem: LexicalItem? = null,
    var previousItem: LexicalItem? = null,
)
