package tech.zingu.kontent.templates

import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.title

fun errorPage(message: String) = html {
    head {
        title("Error")
    }
    body {
        h1 { +message }
    }
}
