package org.composedesktopapp.project.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.composedesktopapp.project.DrawingStroke
import org.composedesktopapp.project.PurpleGrey40
import org.composedesktopapp.project.PurpleGrey80
import org.composedesktopapp.project.palette
import kotlin.math.roundToInt

class DrawingApp() : Screen{
    @Composable
    override fun Content() {
        val strokes = remember { mutableStateListOf<DrawingStroke>() }
        var currentStroke by remember { mutableStateOf<DrawingStroke?>(null) }
        var selectedColor by remember { mutableStateOf(Color.White) }
        var strokeWidth by remember { mutableStateOf(4f) }

        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xFFE0E0E0)),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().background(Color(0xFF2D2D2D)).padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (color in palette){
                    Box(
                        modifier = Modifier.size(32.dp)
                            .clip(CircleShape)
                            .background(color)
                            .then(
                                if (selectedColor == color)
                                    Modifier.border(3.dp, Color.White, CircleShape)
                                else
                                    Modifier.border(1.dp, PurpleGrey40, CircleShape))
                            .clickable { selectedColor = color }
                    )
                }
                Spacer(Modifier.width(16.dp))
                //Stroke with Slider
                Text("width: $strokeWidth", color = Color.White)
                Slider(
                    value = strokeWidth,
                    onValueChange = { width -> strokeWidth = width.roundToInt().toFloat()},
                    valueRange = 2f..32f,
                    modifier = Modifier.width(150.dp)

                )
                Spacer(Modifier.weight(1f))
                IconButton(
                    onClick = { if(strokes.isNotEmpty()) strokes.removeLast() },
                ){
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "undo")
                }
                IconButton(
                    onClick = { strokes.clear() },
                ){
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = PurpleGrey80)
                }
            }
            // Drawing Canvas
            DrawingCanvas(
                strokes = strokes,
                currentStroke = currentStroke,
                onStrokeStart = { offset ->
                    currentStroke = DrawingStroke(
                        points = listOf(offset),
                        color = selectedColor,
                        width = strokeWidth
                    )
                },
                onStrokeDrag = { offset ->
                    currentStroke = currentStroke?.copy(
                        points = currentStroke!!.points + offset
                    )
                },
                onStrokeEnd = {
                    currentStroke?.let { strokes.add(it) }
                    currentStroke = null
                },
                modifier = Modifier.weight(1f),
            )
        }
    }
}
