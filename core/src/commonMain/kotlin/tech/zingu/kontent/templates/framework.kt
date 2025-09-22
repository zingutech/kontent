package tech.zingu.kontent.templates

import kotlinx.html.HTML
import kotlinx.html.stream.createHTML
import tech.zingu.kontent.Config

interface Template<T> {
    fun templateSample(): String
    fun parseData(configData: String): T
    /** The HTML layout structure of the template. */
    fun layout(config: Config<T>): HTML
    fun resourcesFolder(): String? = null
}

fun html(block: HTML.() -> Unit): HTML {
    val html = HTML(initialAttributes = emptyMap(), consumer = createHTML())
    html.block()
    return html
}

internal fun HTML.print(): String = buildString {
    appendLine("<!DOCTYPE html>")
    appendLine(this@print.toString())
}
