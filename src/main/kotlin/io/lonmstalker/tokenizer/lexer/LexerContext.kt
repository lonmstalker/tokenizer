package io.lonmstalker.tokenizer.lexer

import io.lonmstalker.tokenizer.enums.Tokens
import io.lonmstalker.tokenizer.front.FunctionContext

data class LexerContext(
    val functionContext: FunctionContext,

    var nextLine: Boolean = false,
    var isFindArgs: Boolean = false,
    var currentFlow: Tokens.Flow? = null,

    var endNextItem: Boolean = false,
    var startNextItem: Boolean = false,

    var currentLine: Int,
    var endIndex: Int = 0,
    var startIndex: Int = 0,

    var currentItem: LexicalItem? = null,
    var previousItem: LexicalItem? = null,
)
