package tech.zingu.kontent

import tech.zingu.kontent.commands.build
import tech.zingu.kontent.commands.create
import tech.zingu.kontent.commands.printBuildCommandHelp
import tech.zingu.kontent.commands.printCreateCommandHelp
import tech.zingu.kontent.commands.printServeCommandHelp
import tech.zingu.kontent.commands.serve

object CommandLine {
    fun execute(args: Array<String>) {
        if (args.isEmpty()) {
            throw CommandLineException("No command specified. Use --help for usage information.")
        }

        when (val command = args[0].lowercase()) {
            "build" -> build(args)
            "create" -> create(args)
            "serve" -> serve(args)
            "--help", "-h" -> showHelp()
            else -> throw CommandLineException("Unknown command: $command")
        }
    }

    private fun showHelp() = println("""
        Kontent CLI

        Usage:
          kontent <command> [arguments]
        
        Commands:
          ${printBuildCommandHelp()}
          ${printCreateCommandHelp()}
          ${printServeCommandHelp()}
          --help, -h      Show this help message
        
        Examples:
          kontent create my-new-website
    """.trimIndent())
}
