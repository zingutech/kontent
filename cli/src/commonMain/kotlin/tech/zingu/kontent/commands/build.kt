package tech.zingu.kontent.commands

import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.files.SystemPathSeparator
import tech.zingu.kontent.CommandLineException

fun build(args: Array<String>) {
    val outputDir = args.getOrNull(1) ?: "output"

    if (outputDir.first() in setOf(SystemPathSeparator)) {
        throw CommandLineException("Output dir must be a valid directory name")
    }

    val outputDirPath = SystemFileSystem.createDirectories(Path(outputDir))
}

fun printBuildCommandHelp(): String = """
    build <output dir>    Generates the static content on the specified output directory.

    Examples:
      kontent build ./output-dir
""".trimIndent()
