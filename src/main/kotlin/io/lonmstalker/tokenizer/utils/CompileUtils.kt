package io.lonmstalker.tokenizer.utils

import io.lonmstalker.tokenizer.exception.CompileException
import io.lonmstalker.tokenizer.lexer.LexerContext

fun LexerContext.compileError(message: String) {
    compileError(this.currentLine, this.startIndex, this.endIndex, message)
}

fun compileError(line: Int, startIndex: Int, endIndex: Int, message: String) {
    throw CompileException("${getLineMessage(line, startIndex, endIndex)}, message: $message")
}

fun getLineMessage(line: Int, startIndex: Int, endIndex: Int) = "line: $line, index: $startIndex-$endIndex"