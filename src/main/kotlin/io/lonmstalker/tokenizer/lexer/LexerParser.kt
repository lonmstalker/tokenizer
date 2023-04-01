package io.lonmstalker.tokenizer.lexer

import io.lonmstalker.tokenizer.front.FunctionContext
import io.netty.util.internal.StringUtil.SPACE

/**
 * Parser of text to lexical items action tree
 */
class LexerParser {
    private val tokenFactory = TokenFactory()

    /**
     * @throws io.lonmstalker.tokenizer.exception.CompileException - token not supported or reserved word
     */
    fun parse(functionBlock: String, ctx: FunctionContext, startLine: Int) {
        val chars = functionBlock.toCharArray()
        val lexerContext = LexerContext(currentLine = startLine, functionContext = ctx)

        chars.forEach loop@{ char ->
            if (!lexerContext.isStart && !char.isWhitespace()) {
                lexerContext.isStart = true
            }

            lexerContext.shiftIndex(char) { return@loop }

            if (lexerContext.endNextItem) {
                lexerContext.addNextItem(chars)
            }

            lexerContext.endNextItem = false
        }
    }

    /**
     * Shift start and end indexes, that use for find tokens
     */
    private inline fun LexerContext.shiftIndex(char: Char, continueFunc: () -> Unit) {
        if (char == SPACE) {
            if (!this.startNextItem) {
                this.endNextItem = true
            }
            if (this.isStart) {
                this.startNextItem = true
            }
            this.startIndex++
            continueFunc.invoke()
        } else if (char.isWhitespace()) {
            this.nextLine = true
        } else {
            this.endIndex++
            this.startNextItem = false
        }
    }

    /**
     * Added new item in a tree
     */
    private fun LexerContext.addNextItem(chars: CharArray) {
        val prePreviousItem = this.previousItem

        this.previousItem = this.currentItem
        this.currentItem = this.toItem(chars)

        this.previousItem?.let {
            it.add(prePreviousItem, this.currentItem)
            checkSupport(it)
        }
    }

    private fun LexerContext.toItem(chars: CharArray): LexicalItem =
        if (this.endIndex - this.startIndex == 1) {
            tokenFactory.findToken(chars[this.endIndex])
        } else {
            tokenFactory.findToken(chars.copyOfRange(this.startIndex, this.endIndex))
        }

    /**
     * Can validate only previous, because of when parse current don't know next item
     */
    private fun checkSupport(previousItem: LexicalItem) {
        if (!previousItem.isSupport()) {
            val prev = previousItem.prevToken?.getName()
            val next = previousItem.nextToken?.getName()
            previousItem.invalidateToken("$prev can't apply to $next by ${previousItem.getName()} ")
        }
    }

}