package tech.zingu.kontent

import androidx.compose.runtime.Composable
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.dom.clear
import org.jetbrains.compose.web.renderComposableInBody
import tech.zingu.kontent.pages.Page404
import tech.zingu.kontent.pages.PageHome
import tech.zingu.kontent.pages.handleWindowClickOnHomePage

val appScope = MainScope()

fun main() {
    window.onload = { renderContent() }
    window.onpopstate = { renderContent() }
    window.onclick = { handleWindowClickOnHomePage(it) }
}

sealed class Route(val path: String) {
    companion object {
        fun findByPath(path: String): Route = when {
            // path.startsWith(RouteSignIn.path) -> RouteSignIn
            path == "/" -> RouteHome
            else -> Route404
        }
    }
    fun load() = renderContent(this)
}

data object RouteHome : Route("/")
data object Route404 : Route("/404")

private fun renderContent() = renderContent(
    Route.findByPath(window.location.pathname)
)

private fun renderContent(route: Route) {
    document.body?.clear()
    appScope.launch {
        when {
            route == Route404 && window.location.pathname != Route404.path -> window.location.href = Route404.path
            else -> renderComposableInBody { routeComposable(route) }
        }
    }
}

@Composable
private fun routeComposable(route: Route) = when(route) {
    RouteHome -> PageHome()
    Route404 -> Page404()
}
