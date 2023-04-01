package io.lonmstalker.tokenizer.enums

class Tokens {

    enum class Operators(val symbol: Char, val operation: String) {
        PLUS('+', "plus"),
        MINUS('-', "minus"),
        DIV('/', "division"),
        POW('*', "multiply"),
        ASSIGN('=', "assign"),
        NOT('!', "not")
    }

    enum class Operations(val symbol: String, val operation: String) {
        EQUAL("==", "equals"),
        TO("->", "to")
    }

    enum class Flow(val symbol: Char) {
        START_BLOCK('{'),
        END_BLOCK('}'),
        START_ARG('('),
        END_ARG(')'),
        REFERENCE('.'),
        END_LINE(';'),
        DOUBLE_QUOTE('"')
    }

    enum class Keywords(val word: String) {
        FUNCTION("fn"),
        RETURN("return"),
        VARIABLE("let"),
        TRUE("true"),
        FALSE("false")
    }

    enum class Types(val word: String) {
        NUMBER("Number"),
        NOTHING("Nothing"),
        STRING("String"),
        BOOLEAN("Boolean")
    }
}