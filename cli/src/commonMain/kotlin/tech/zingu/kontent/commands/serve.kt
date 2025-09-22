package tech.zingu.kontent.commands

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import tech.zingu.kontent.CommandLineException
import tech.zingu.kontent.HttpServer

private var server: HttpServer? = null

fun serve(args: Array<String>) {
    if (args.size < 2) {
        throw CommandLineException("'serve' command requires a port parameter")
    }

    val portStr = args[1]
    if (portStr.isBlank()) {
        throw CommandLineException("Port cannot be empty")
    }
    val port = runCatching { portStr.toInt() }.getOrNull() ?:
    throw CommandLineException("Port must be a number")

    runBlocking {
        server = HttpServer(port).also { it.start() }
        // keep the main thread alive, wait indefinitely
        suspendCancellableCoroutine<Unit> { }
    }
}

fun printServeCommandHelp(): String = """
    serve <name>    Create a new item with the specified name

    Examples:
      kontent serve my-new-item
""".trimIndent()
