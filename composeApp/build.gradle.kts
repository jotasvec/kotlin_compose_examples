import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    //serialization plugin added
    kotlin("plugin.serialization") version "2.3.10"
}

kotlin {
    jvm()
    val ktorVersion= "3.4.0" // if it doesn't work change to 3.4.0
    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            //icons
            implementation("org.jetbrains.compose.material:material-icons-core:1.7.3")
            //implementation()

            // Ktor Client
            // Ktor core
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            // Desktop/JVM, 'CIO' (Coroutine I/O)
            implementation("io.ktor:ktor-client-cio:$ktorVersion")
            // Content Negotiation  (send/receive JSON packages)
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            // Serialization JSON (convert objects to text)
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            // Voyager
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.transitions)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}


compose.desktop {
    application {
        mainClass = "org.ComposeDesktopApp.project.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.ComposeDesktopApp.project"
            packageVersion = "1.0.0"

            macOS{
                iconFile.set(project.file("resources/screenshot_icon.png"))
            }
            windows{
                iconFile.set(project.file("resources/screenshot_icon.ico"))
            }
        }
    }
}
