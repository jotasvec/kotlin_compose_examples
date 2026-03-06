package org.composedesktopapp.project.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import org.composedesktopapp.project.DrawingStroke

@Composable
fun DrawingCanvas(
    modifier: Modifier = Modifier,
    currentStroke: DrawingStroke?,
    strokes: List<DrawingStroke>,
    onStrokeStart: (Offset) -> Unit,
    onStrokeDrag: (Offset) -> Unit,
    onStrokeEnd: () -> Unit,
) {
    Canvas(
        modifier = modifier.fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset -> onStrokeStart(offset) },
                    onDrag = { change, _ -> change.consume()
                        onStrokeDrag(change.position)
                    },
                    onDragEnd = {onStrokeEnd()},
                    onDragCancel = { onStrokeEnd() },
                )
            }
    ){
        val allStrokes = if(currentStroke != null) strokes + currentStroke else strokes
        for(stroke in allStrokes){
            if (stroke.points.isEmpty() ) continue
            /*if(stroke.points.size == 1) {
                drawCircle(color = stroke.color, radius = stroke.width / 2, center = stroke.points[0])
                continue
            }*/
            val path = Path().apply {
                moveTo(stroke.points[0].x, stroke.points[0].y)
                for (i in 1 until stroke.points.size){
                    lineTo(stroke.points[i].x, stroke.points[i].y)
                }
            }
            drawPath(
                path = path,
                color = stroke.color,
                style = Stroke(
                    width = stroke.width,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )
        }
    }
}


