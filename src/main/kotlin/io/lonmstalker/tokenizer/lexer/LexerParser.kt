package io.lonmstalker.tokenizer.lexer

import io.lonmstalker.tokenizer.front.FunctionContext
import io.netty.util.internal.StringUtil.SPACE

/**
 * Parser of text to lexical items action tree
 */
class LexerParser {

    fun parse(functionBlock: String, ctx: FunctionContext) {
        var nextItem = true
        var isStart = false

        var endIndex = 0
        var startIndex = 0

        var currentItem: LexicalItem? = null
        var previousItem: LexicalItem? = null

        val chars = functionBlock.toCharArray()
        for ((index, char) in chars.withIndex()) {
            if (char == SPACE) {
                if (isStart) {
                    endIndex = index - 1
                }
                nextItem = true
                startIndex = index + 1
                continue
            }

            if (!isStart) {
                isStart = true
            }

            if (nextItem) {
                val prePreviousItem = previousItem
                previousItem = currentItem
                currentItem = this.toItem(chars.copyOfRange(startIndex, endIndex))

                previousItem?.add(prePreviousItem, currentItem)
                previousItem?.let { this.isSupport(it) }
            }

            nextItem = false
        }

    }

    private fun toItem(chars: CharArray): LexicalItem {

    }

    /**
     * Can validate only previous, because of when parse current don't know next item
     */
    private fun isSupport(previousItem: LexicalItem) {
        if (!previousItem.isSupport()) {
            previousItem.invalidateToken("can't compile")
        }
    }

}