package tech.zingu.kontent.extension

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.ExperimentalComposeWebSvgApi
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.svg.Path
import org.jetbrains.compose.web.svg.Svg
import org.jetbrains.compose.web.svg.fill
import org.jetbrains.compose.web.svg.fillRule
import org.w3c.dom.svg.SVGElement

@Composable
fun HomeIconSvg() = TailwindSvgIcon(
    fill = "none",
    strokeWidth = "1.5",
    stroke = "currentColor",
    paths = listOf(
        path(
            d = "M2.25 12l8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25",
            strokeLinecap = "round",
            strokeLinejoin = "round"
        )
    )
)

@Composable
fun TeamIconSvg() = TailwindSvgIcon(
    fill = "none",
    strokeWidth = "1.5",
    stroke = "currentColor",
    paths = listOf(
        path(
            d = "M15 19.128a9.38 9.38 0 002.625.372 9.337 9.337 0 004.121-.952 4.125 4.125 0 00-7.533-2.493M15 19.128v-.003c0-1.113-.285-2.16-.786-3.07M15 19.128v.106A12.318 12.318 0 018.624 21c-2.331 0-4.512-.645-6.374-1.766l-.001-.109a6.375 6.375 0 0111.964-3.07M12 6.375a3.375 3.375 0 11-6.75 0 3.375 3.375 0 016.75 0zm8.25 2.25a2.625 2.625 0 11-5.25 0 2.625 2.625 0 015.25 0z",
            strokeLinecap = "round",
            strokeLinejoin = "round"
        )
    )
)

@Composable
fun FolderIconSvg() = TailwindSvgIcon(
    fill = "none",
    strokeWidth = "1.5",
    stroke = "currentColor",
    paths = listOf(
        path(
            d = "M2.25 12.75V12A2.25 2.25 0 014.5 9.75h15A2.25 2.25 0 0121.75 12v.75m-8.69-6.44l-2.12-2.12a1.5 1.5 0 00-1.061-.44H4.5A2.25 2.25 0 002.25 6v12a2.25 2.25 0 002.25 2.25h15A2.25 2.25 0 0021.75 18V9a2.25 2.25 0 00-2.25-2.25h-5.379a1.5 1.5 0 01-1.06-.44z",
            strokeLinecap = "round",
            strokeLinejoin = "round"
        )
    )
)

@Composable
fun CalendarIconSvg() = TailwindSvgIcon(
    fill = "none",
    strokeWidth = "1.5",
    stroke = "currentColor",
    paths = listOf(
        path(
            d = "M6.75 3v2.25M17.25 3v2.25M3 18.75V7.5a2.25 2.25 0 012.25-2.25h13.5A2.25 2.25 0 0121 7.5v11.25m-18 0A2.25 2.25 0 005.25 21h13.5A2.25 2.25 0 0021 18.75m-18 0v-7.5A2.25 2.25 0 015.25 9h13.5A2.25 2.25 0 0121 11.25v7.5",
            strokeLinecap = "round",
            strokeLinejoin = "round"
        )
    )
)

@Composable
fun DocumentsIconSvg() = TailwindSvgIcon(
    fill = "none",
    strokeWidth = "1.5",
    stroke = "currentColor",
    paths = listOf(
        path(
            d = "M15.75 17.25v3.375c0 .621-.504 1.125-1.125 1.125h-9.75a1.125 1.125 0 01-1.125-1.125V7.875c0-.621.504-1.125 1.125-1.125H6.75a9.06 9.06 0 011.5.124m7.5 10.376h3.375c.621 0 1.125-.504 1.125-1.125V11.25c0-4.46-3.243-8.161-7.5-8.876a9.06 9.06 0 00-1.5-.124H9.375c-.621 0-1.125.504-1.125 1.125v3.5m7.5 10.375H9.375a1.125 1.125 0 01-1.125-1.125v-9.25m12 6.625v-1.875a3.375 3.375 0 00-3.375-3.375h-1.5a1.125 1.125 0 01-1.125-1.125v-1.5a3.375 3.375 0 00-3.375-3.375H9.75",
            strokeLinecap = "round",
            strokeLinejoin = "round"
        )
    )
)

@Composable
fun PieChartIconSvg() = TailwindSvgIcon(
    fill = "none",
    strokeWidth = "1.5",
    stroke = "currentColor",
    paths = listOf(
        path(
            d = "M10.5 6a7.5 7.5 0 107.5 7.5h-7.5V6z",
            strokeLinecap = "round",
            strokeLinejoin = "round"
        ),
        path(
            d = "M13.5 10.5H21A7.5 7.5 0 0013.5 3v7.5z",
            strokeLinecap = "round",
            strokeLinejoin = "round"
        )
    )
)

@Composable
fun GearIconSvg() = TailwindSvgIcon(
    fill = "none",
    strokeWidth = "1.5",
    stroke = "currentColor",
    paths = listOf(
        path(
            d = "M9.594 3.94c.09-.542.56-.94 1.11-.94h2.593c.55 0 1.02.398 1.11.94l.213 1.281c.063.374.313.686.645.87.074.04.147.083.22.127.324.196.72.257 1.075.124l1.217-.456a1.125 1.125 0 011.37.49l1.296 2.247a1.125 1.125 0 01-.26 1.431l-1.003.827c-.293.24-.438.613-.431.992a6.759 6.759 0 010 .255c-.007.378.138.75.43.99l1.005.828c.424.35.534.954.26 1.43l-1.298 2.247a1.125 1.125 0 01-1.369.491l-1.217-.456c-.355-.133-.75-.072-1.076.124a6.57 6.57 0 01-.22.128c-.331.183-.581.495-.644.869l-.213 1.28c-.09.543-.56.941-1.11.941h-2.594c-.55 0-1.02-.398-1.11-.94l-.213-1.281c-.062-.374-.312-.686-.644-.87a6.52 6.52 0 01-.22-.127c-.325-.196-.72-.257-1.076-.124l-1.217.456a1.125 1.125 0 01-1.369-.49l-1.297-2.247a1.125 1.125 0 01.26-1.431l1.004-.827c.292-.24.437-.613.43-.992a6.932 6.932 0 010-.255c.007-.378-.138-.75-.43-.99l-1.004-.828a1.125 1.125 0 01-.26-1.43l1.297-2.247a1.125 1.125 0 011.37-.491l1.216.456c.356.133.751.072 1.076-.124.072-.044.146-.087.22-.128.332-.183.582-.495.644-.869l.214-1.281z",
            strokeLinecap = "round",
            strokeLinejoin = "round"
        ),
        path(
            d = "M15 12a3 3 0 11-6 0 3 3 0 016 0z",
            strokeLinecap = "round",
            strokeLinejoin = "round"
        )
    )
)

@Composable
fun ListMenuIconSvg() = TailwindSvgIcon(
    classes = listOf("h-6", "w-6"),
    fill = "none",
    strokeWidth = "1.5",
    stroke = "currentColor",
    paths = listOf(
        path(
            d = "M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5",
            strokeLinecap = "round",
            strokeLinejoin = "round"
        )
    )
)

@Composable
fun MagnifierIconSvg() = TailwindSvgIcon(
    viewBox = "0 0 20 20",
    classes = listOf("pointer-events-none", "absolute", "inset-y-0", "left-0", "h-full", "w-5", "text-gray-400"),
    fill = "currentColor",
    paths = listOf(
        path(
            d = "M9 3.5a5.5 5.5 0 100 11 5.5 5.5 0 000-11zM2 9a7 7 0 1112.452 4.391l3.328 3.329a.75.75 0 11-1.06 1.06l-3.329-3.328A7 7 0 012 9z",
            fillRule = "evenodd",
            clipRule = "evenodd"
        )
    )
)

@Composable
fun BellIconSvg() = TailwindSvgIcon(
    classes = listOf("h-6", "w-6"),
    fill = "none",
    strokeWidth = "1.5",
    stroke = "currentColor",
    paths = listOf(
        path(
            d = "M14.857 17.082a23.848 23.848 0 005.454-1.31A8.967 8.967 0 0118 9.75v-.7V9A6 6 0 006 9v.75a8.967 8.967 0 01-2.312 6.022c1.733.64 3.56 1.085 5.455 1.31m5.714 0a24.255 24.255 0 01-5.714 0m5.714 0a3 3 0 11-5.714 0",
            strokeLinecap = "round",
            strokeLinejoin = "round"
        )
    )
)

@Composable
fun ArrowDownIconSvg() = TailwindSvgIcon(
    viewBox = "0 0 20 20",
    classes = listOf("ml-2", "h-5", "w-5", "text-gray-400"),
    fill = "currentColor",
    paths = listOf(
        path(
            d = "M5.23 7.21a.75.75 0 011.06.02L10 11.168l3.71-3.938a.75.75 0 111.08 1.04l-4.25 4.5a.75.75 0 01-1.08 0l-4.25-4.5a.75.75 0 01.02-1.06z",
            fillRule = "evenodd",
            clipRule = "evenodd"
        )
    )
)

@Composable
fun CloseIconSvg() = TailwindSvgIcon(
    classes = listOf("h-6", "w-6", "text-white"),
    fill = "none",
    strokeWidth = "1.5",
    stroke = "currentColor",
    paths = listOf(
        path(
            d = "M6 18L18 6M6 6l12 12",
            strokeLinecap = "round",
            strokeLinejoin = "round"
        )
    )
)

@Composable
fun GoogleIconSvg() = TailwindSvgIcon(
    classes = listOf("h-5", "w-5"),
    paths = listOf(
        path(
            d = "M12.0003 4.75C13.7703 4.75 15.3553 5.36002 16.6053 6.54998L20.0303 3.125C17.9502 1.19 15.2353 0 12.0003 0C7.31028 0 3.25527 2.69 1.28027 6.60998L5.27028 9.70498C6.21525 6.86002 8.87028 4.75 12.0003 4.75Z",
            fill = "#EA4335"
        ),
        path(
            d = "M23.49 12.275C23.49 11.49 23.415 10.73 23.3 10H12V14.51H18.47C18.18 15.99 17.34 17.25 16.08 18.1L19.945 21.1C22.2 19.01 23.49 15.92 23.49 12.275Z",
            fill = "#4285F4"
        ),
        path(
            d = "M5.26498 14.2949C5.02498 13.5699 4.88501 12.7999 4.88501 11.9999C4.88501 11.1999 5.01998 10.4299 5.26498 9.7049L1.275 6.60986C0.46 8.22986 0 10.0599 0 11.9999C0 13.9399 0.46 15.7699 1.28 17.3899L5.26498 14.2949Z",
            fill = "#FBBC05"
        ),
        path(
            d = "M12.0004 24.0001C15.2404 24.0001 17.9654 22.935 19.9454 21.095L16.0804 18.095C15.0054 18.82 13.6204 19.245 12.0004 19.245C8.8704 19.245 6.21537 17.135 5.2654 14.29L1.27539 17.385C3.25539 21.31 7.3104 24.0001 12.0004 24.0001Z",
            fill = "#34A853"
        )
    )
)

@Composable
fun GithubIconSvg() = TailwindSvgIcon(
    viewBox = "0 0 20 20",
    classes = listOf("h-5", "w-5", "fill-[#24292F]"),
    fill = "currentColor",
    paths = listOf(
        path(
            d = "M10 0C4.477 0 0 4.484 0 10.017c0 4.425 2.865 8.18 6.839 9.504.5.092.682-.217.682-.483 0-.237-.008-.868-.013-1.703-2.782.605-3.369-1.343-3.369-1.343-.454-1.158-1.11-1.466-1.11-1.466-.908-.62.069-.608.069-.608 1.003.07 1.531 1.032 1.531 1.032.892 1.53 2.341 1.088 2.91.832.092-.647.35-1.088.636-1.338-2.22-.253-4.555-1.113-4.555-4.951 0-1.093.39-1.988 1.029-2.688-.103-.253-.446-1.272.098-2.65 0 0 .84-.27 2.75 1.026A9.564 9.564 0 0110 4.844c.85.004 1.705.115 2.504.337 1.909-1.296 2.747-1.027 2.747-1.027.546 1.379.203 2.398.1 2.651.64.7 1.028 1.595 1.028 2.688 0 3.848-2.339 4.695-4.566 4.942.359.31.678.921.678 1.856 0 1.338-.012 2.419-.012 2.747 0 .268.18.58.688.482A10.019 10.019 0 0020 10.017C20 4.484 15.522 0 10 0z",
            fillRule = "evenodd",
            clipRule = "evenodd"
        )
    )
)

@Composable
@OptIn(ExperimentalComposeWebSvgApi::class)
private fun TailwindSvgIcon(
    viewBox: String = "0 0 24 24",
    classes: List<String> = listOf("h-6", "w-6", "shrink-0"),
    fill: String? = null,
    strokeWidth: String? = null,
    stroke: String? = null,
    ariaHidden: String = "true",
    paths: List<Path>,
) {
    Svg(
        viewBox = viewBox,
        attrs = {
            classes(classes)
            fill?.let { this.fill(it) }
            strokeWidth?.let { strokeWidth(it) }
            stroke?.let { stroke(it) }
            ariaHidden(ariaHidden)
        }
    ) {
        paths.forEach { path ->
            Path(
                d = path.d,
                attrs = {
                    path.clipRule?.let { clipRule(it) }
                    path.fill?.let { fill(it) }
                    path.fillRule?.let { fillRule(it) }
                    path.strokeLinecap?.let { strokeLinecap(it) }
                    path.strokeLinejoin?.let { strokeLinejoin(it) }
                }
            )
        }
    }
}

private fun path(
    d: String,
    clipRule: String? = null,
    fill: String? = null,
    fillRule: String? = null,
    strokeLinecap: String? = null,
    strokeLinejoin: String? = null,
) = Path(
    d = d,
    clipRule = clipRule,
    fill = fill,
    fillRule = fillRule,
    strokeLinecap = strokeLinecap,
    strokeLinejoin = strokeLinejoin
)

private data class Path(
    val d: String,
    val clipRule: String?,
    val fill: String?,
    val fillRule: String?,
    val strokeLinecap: String?,
    val strokeLinejoin: String?
)

private fun AttrsScope<SVGElement>.clipRule(value: String) =
    attr("clip-rule", value)

private fun AttrsScope<SVGElement>.stroke(value: String) =
    attr("stroke", value)

private fun AttrsScope<SVGElement>.strokeWidth(value: String) =
    attr("stroke-width", value)

private fun AttrsScope<SVGElement>.strokeLinecap(value: String) =
    attr("stroke-linecap", value)

private fun AttrsScope<SVGElement>.strokeLinejoin(value: String) =
    attr("stroke-linejoin", value)
