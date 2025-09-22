plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    listOf(
        macosX64(),
        macosArm64(),
        linuxX64(),
        linuxArm64()
    ).forEach {
        it.binaries {
            executable {
                entryPoint = "tech.zingu.kontent.main"
                baseName = "kontent"
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(kotlin("stdlib"))
            implementation(project(":core"))
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.html)
            implementation(libs.kotlinx.io)
            implementation(libs.ktor.server.core)
            implementation(libs.ktor.server.cio)
            implementation(libs.ktor.server.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        val macosMain by creating {
            dependsOn(commonMain.get())
        }
        val macosArm64Main by getting {
            dependsOn(macosMain)
        }
        val macosX64Main by getting {
            dependsOn(macosMain)
        }

        val linuxMain by creating {
            dependsOn(commonMain.get())
        }
        val linuxX64Main by getting {
            dependsOn(linuxMain)
        }
        val linuxArm64Main by getting {
            dependsOn(linuxMain)
        }
    }
}

// task to copy executables to a distribution directory
tasks.register<Copy>("createDistribution") {
    dependsOn(
        "macosArm64Binaries",
        "macosX64Binaries",
        "linuxX64Binaries",
        "linuxArm64Binaries"
    )

    // macOS binaries
    from(layout.buildDirectory.dir("bin/macosArm64/releaseExecutable/kontent.kexe")) {
        rename { "kontent-macos-arm64" }
    }
    from(layout.buildDirectory.dir("bin/macosX64/releaseExecutable/kontent.kexe")) {
        rename { "kontent-macos-x64" }
    }

    // linux binaries
    from(layout.buildDirectory.dir("bin/linuxX64/releaseExecutable/kontent.kexe")) {
        rename { "kontent-linux-x64" }
    }
    from(layout.buildDirectory.dir("bin/linuxArm64/releaseExecutable/kontent.kexe")) {
        rename { "kontent-linux-arm64" }
    }

    into(layout.buildDirectory.dir("dist"))

    // create a universal launcher script
    doLast {
        val distDir = layout.buildDirectory.dir("dist").get().asFile
        val launcherScript = distDir.resolve("kontent")

        launcherScript.writeText("""
            #!/bin/bash
            SCRIPT_DIR="${'$'}(cd "${'$'}(dirname "${'$'}0")" && pwd)"
            
            # Detect OS and architecture
            OS="${'$'}(uname -s)"
            ARCH="${'$'}(uname -m)"
            
            if [ "${'$'}OS" = "Darwin" ]; then
                if [ "${'$'}ARCH" = "arm64" ]; then
                    EXEC="${'$'}SCRIPT_DIR/kontent-macos-arm64"
                else
                    EXEC="${'$'}SCRIPT_DIR/kontent-macos-x64"
                fi
            elif [ "${'$'}OS" = "Linux" ]; then
                if [ "${'$'}ARCH" = "aarch64" ]; then
                    EXEC="${'$'}SCRIPT_DIR/kontent-linux-arm64"
                else
                    EXEC="${'$'}SCRIPT_DIR/kontent-linux-x64"
                fi
            else
                echo "Unsupported operating system: ${'$'}OS"
                exit 1
            fi
            
            if [ ! -f "${'$'}EXEC" ]; then
                echo "Missing executable for ${'$'}OS/${'$'}ARCH: ${'$'}EXEC"
                exit 1
            fi
            
            chmod +x "${'$'}EXEC"
            exec "${'$'}EXEC" "${'$'}@"
        """.trimIndent())

        launcherScript.setExecutable(true)
    }
}
