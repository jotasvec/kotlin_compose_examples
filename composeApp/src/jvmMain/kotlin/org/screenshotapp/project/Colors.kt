package org.screenshotapp.project

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 1. Tus colores base
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Purple40 = Color(0xFF6650A4)
val PurpleGrey40 = Color(0xFF625B71)

val DarkBackground = Color(0xFF1C1B1F)
val DarkSurface = Color(0xFF2B2B2B)

val LightBackground = Color(0xFFFFFBFE) // Añadido para el modo claro
val LightSurface = Color(0xFFFFFBFE)

// 2. Paleta para el Modo Oscuro
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = Color.Black // Color del texto sobre botones primarios
)

// 3. Paleta para el Modo Claro
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    background = LightBackground,
    surface = LightSurface,
    onPrimary = Color.White
)

// 4. Tu componente envolvedor (Wrapper) del Tema
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Detecta el SO automáticamente
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}