package org.composedesktopapp.project


import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "App with testing purposes",
    ) {
        // === APP trigger ====
        App()
    }
}