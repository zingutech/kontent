import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    js {
        outputModuleName = "kontent"
        browser {
            testTask {
                useKarma {
                    useChromiumHeadless()
                    // useFirefoxHeadless()
                }
            }
        }
        binaries.executable()
        //generateTypeScriptDefinitions()
        //compilerOptions { target = "es2015" }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
            implementation(libs.kotlinx.serialization)
            implementation(libs.kotlinx.datetime)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        jsMain.dependencies {
            // compose
            implementation(compose.html.core)
            implementation(compose.html.svg)
            implementation(compose.runtime)

            // datetime impl
            implementation(npm(libs.js.joda))

            // ktor
            implementation(libs.ktor.client.js)

            // tailwind
            implementation(npm(libs.tailwindcss.core))
            implementation(npm(libs.tailwindcss.typography))
            implementation(npm(libs.tailwindcss.forms))

            // webpack
            implementation(npm(libs.postcss.core))
            implementation(npm(libs.postcss.loader))
            implementation(npm(libs.autoprefixer))
            implementation(npm(libs.css.loader))
            implementation(npm(libs.style.loader))
            implementation(npm(libs.cssnano))
        }

        val jsTest by getting {
        }
    }
}

// FIXME: Simple workaround to make version catalogs usable for npm dependencies too.
//  Remove if kotlin plugin supports this out of the box!
fun KotlinDependencyHandler.npm(dependency: Provider<MinimalExternalModuleDependency>): Dependency =
    dependency.map { dep ->
        val name = if (dep.group == "npm") dep.name else "@${dep.group}/${dep.name}"
        npm(name, dep.version!!)
    }.get()
