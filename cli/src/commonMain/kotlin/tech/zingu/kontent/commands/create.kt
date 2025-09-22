package tech.zingu.kontent.commands

import tech.zingu.kontent.CommandLineException

fun create(args: Array<String>) {
    if (args.size < 2) {
        throw CommandLineException("'create' command requires a name parameter")
    }

    val name = args[1]
    if (name.isBlank()) {
        throw CommandLineException("Name cannot be empty")
    }

    println("Creating new item: $name")
    // TODO: Implement the actual creation logic
}

fun printCreateCommandHelp(): String = """
    create <name>    Create a new item with the specified name

    Examples:
      kontent create my-new-item
""".trimIndent()
