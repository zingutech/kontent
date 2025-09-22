package tech.zingu.kontent.extension

import org.w3c.dom.DOMTokenList

fun DOMTokenList.addIfNotExist(vararg tokens: String) {
    tokens.forEach {
        if (!contains(it)) {
            add(it)
        }
    }
}

fun DOMTokenList.replaceOrAddIfNotExist(vararg tokens: Pair<String, String>) {
    tokens.forEach { (token, newToken) ->
        if (contains(token)) {
            replace(token, newToken)
        } else {
            add(newToken)
        }
    }
}
