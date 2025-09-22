plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    js {
        browser {
            testTask {
                useKarma()
            }
        }
        //generateTypeScriptDefinitions()
        //compilerOptions { target = "es2015" }
    }
    macosX64()
    macosArm64()
    linuxX64()
    linuxArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(kotlin("stdlib"))
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.html)
            implementation(libs.kotlinx.io)
            implementation(libs.kotlinx.serialization)
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
