package tech.zingu.kontent

fun main(args: Array<String>) {
    try {
        CommandLine.execute(args)
    } catch (e: CommandLineException) {
        println("Error: ${e.message}")
        exitProcess(1)
    }
}

internal fun exitProcess(status: Int): Nothing {
    kotlin.system.exitProcess(status)
}
