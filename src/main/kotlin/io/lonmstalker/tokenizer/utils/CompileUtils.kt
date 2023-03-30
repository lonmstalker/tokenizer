package io.lonmstalker.tokenizer.utils

import io.lonmstalker.tokenizer.exception.CompileException
import io.lonmstalker.tokenizer.lexer.LexerContext

object CompileUtils {

    @JvmStatic
    fun LexerContext.compileError(message: String) {
        compileError(this.currentLine, this.startIndex, this.endIndex, message)
    }

    @JvmStatic
    fun compileError(line: Int, startIndex: Int, endIndex: Int, message: String) {
        throw CompileException("line: $line, index: $startIndex-$endIndex, message: $message")
    }
}