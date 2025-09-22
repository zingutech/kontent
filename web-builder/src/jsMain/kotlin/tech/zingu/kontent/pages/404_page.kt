package tech.zingu.kontent.pages

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.*
import tech.zingu.kontent.extension.ariaHidden

@Composable
fun Page404() {
    Main(
        attrs = {
            classes("grid", "min-h-full", "place-items-center", "bg-gray-900", "px-6", "py-24", "sm:py-32", "lg:px-8")
        }
    ) {
        Div(attrs = { classes("text-center") }) {
            P(attrs = { classes("text-base", "font-semibold", "text-indigo-400") }) {
                Text("404")
            }
            H1(attrs = { classes("mt-4", "text-3xl", "font-bold", "tracking-tight", "text-white", "sm:text-5xl") }) {
                Text("Page not found")
            }
            P(attrs = { classes("mt-6", "text-base", "leading-7", "text-gray-400") }) {
                Text("Sorry, we couldn’t find the page you’re looking for.")
            }
            Div(attrs = { classes("mt-10", "flex", "items-center", "justify-center", "gap-x-6") }) {
                A(href = "/",
                  attrs = {
                      classes("rounded-md", "bg-indigo-600", "px-3.5", "py-2.5", "text-sm", "font-semibold", "text-white", "shadow-sm", "hover:bg-indigo-500", "focus-visible:outline", "focus-visible:outline-2", "focus-visible:outline-offset-2", "focus-visible:outline-indigo-600")
                  }
                ) {
                    Text("Go back home")
                }
                A(href = "#",
                  attrs = { classes("text-sm", "font-semibold", "text-white") }
                ) {
                    Text("Contact support ")
                    Span(attrs = { ariaHidden("true") }) { Text("→") }
                }
            }
        }
    }
}
