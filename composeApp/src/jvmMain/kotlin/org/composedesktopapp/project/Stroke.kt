package org.composedesktopapp.project

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

data class DrawingStroke(
    val points: List<Offset>,
    val color: Color,
    val width: Float,
)

val palette = listOf(
    Color.Red,
    Color.Blue,
    Color.Green,
    Color.Cyan,
    Color.Magenta,
    Purple80,
    Color.Yellow,
    Color(0xFFFF8800), // Orange
    Color.White
)