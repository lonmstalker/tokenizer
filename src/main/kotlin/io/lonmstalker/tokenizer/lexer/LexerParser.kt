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
    fun parse(functionBlock: String, ctx: FunctionContext, startLine: Int): LexicalItem? {
        val chars = functionBlock.toCharArray()
        val lexerCtx = LexerContext(currentLine = startLine, functionContext = ctx)

        for ((index, char) in chars.withIndex()) {
            if (char == SPACE) {
                this.shiftSpace(lexerCtx, index)
            } else if (char.isWhitespace()) {
                lexerCtx.currentLine = lexerCtx.currentLine + 1
                this.shiftSpace(lexerCtx, index)
            } else {
                this.shiftSymbol(lexerCtx, index)
            }
            this.nextItem(lexerCtx, chars)
        }

        this.shiftSpace(lexerCtx, chars.size) // in copy of range toIndex is exclusive
        this.nextItem(lexerCtx, chars)

        return lexerCtx.previousItem
    }

    private fun nextItem(lexerCtx: LexerContext, chars: CharArray) {
        if (lexerCtx.endNextItem) {
            lexerCtx.addNextItem(chars)
        }
    }

    private fun shiftSymbol(ctx: LexerContext, index: Int) {
        if (ctx.endNextItem) {
            ctx.startIndex = index
        }
        ctx.endNextItem = false
        ctx.startNextItem = true
    }

    private fun shiftSpace(ctx: LexerContext, index: Int) {
        if (ctx.startNextItem) {
            ctx.endIndex = index
            ctx.endNextItem = true
            ctx.startNextItem = false
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
            tokenFactory.findToken(chars[this.endIndex - 1], this)
        } else {
            val symbol = chars.copyOfRange(this.startIndex, this.endIndex)
            this.startIndex = this.startIndex + 1
            tokenFactory.findToken(symbol, this)
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