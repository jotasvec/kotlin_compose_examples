package org.screenshotapp.project.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.HorizontalScrollbar
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow


class ExampleScreen(val id: String) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,

        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Button(onClick = { navigator.pop() }) {
                    Text("Go Back")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Tooltips()
                Spacer(modifier = Modifier.height(12.dp))
                ScrollableList()
                //LazyScrollableList()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScrollableList(){
    val verticalScroll = rememberScrollState(0)
    val horizontalScroll = rememberScrollState(0)

    Box(
        Modifier
            .fillMaxSize()
            .padding(all = 12.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 12.dp, bottom = 12.dp)
                .verticalScroll(verticalScroll)
                .horizontalScroll(horizontalScroll)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            for (item in 0 until 20) {
                var hovered by remember { mutableStateOf(false) }
                val animatedColor by animateColorAsState(
                    targetValue = if (hovered) Color.Cyan else Color.Transparent,
                    animationSpec = tween(200, easing = LinearOutSlowInEasing)
                )
                Text(modifier = Modifier.padding(8.dp)
                    .background(animatedColor)
                    .fillMaxSize()
                    .onPointerEvent(PointerEventType.Enter){ hovered = true }
                    .onPointerEvent(PointerEventType.Exit){ hovered = false },
                    text = "This is the Item number: #$item"
                )
            }
        }
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd)
                .fillMaxHeight(),
            adapter = rememberScrollbarAdapter(verticalScroll)
        )
          HorizontalScrollbar(
            modifier = Modifier.align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(12.dp),
            adapter = rememberScrollbarAdapter(horizontalScroll)
        )

    }
}

@Composable
fun LazyScrollableList(){
    val lazyVerticalScroll = rememberLazyListState()
    val horizontalScroll = rememberScrollState(0)

    Box(
        Modifier
            .fillMaxSize()
            .padding(all = 12.dp),

    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 12.dp, bottom = 12.dp)
                .horizontalScroll(horizontalScroll),
            state = lazyVerticalScroll

        ) {
           items(30) { item ->
                Text(modifier = Modifier.padding(12.dp),
                    text = "This is the Item number: #$item from the items list"
                )
            }
        }
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd)
                .fillMaxHeight(),
            adapter = rememberScrollbarAdapter(lazyVerticalScroll)
        )
        HorizontalScrollbar(
            modifier = Modifier.align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(12.dp),
            adapter = rememberScrollbarAdapter(horizontalScroll)
        )

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tooltips(){
    val buttons = listOf("Contact us", "About")

    Row (
        horizontalArrangement = Arrangement.Center,
    ) {
        buttons.forEachIndexed { index, title ->
            TooltipArea(
                tooltip = {
                // Composable tooltip content
                    Surface(
                        color =  Color(255,255,210), //Color.Yellow,
                        modifier = Modifier.shadow(4.dp),
                        shape = RoundedCornerShape(4.dp),

                    ){
                        Text(
                            text = if (index == 0) "Get in Touch!" else "This is our team",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                },
                delayMillis = 500,
                tooltipPlacement = TooltipPlacement.CursorPoint(
                    alignment = Alignment.BottomEnd,
                )
            ){
                Button(onClick = {}){
                    Text(title)
                }
            }
            if (index == 0){
                Spacer(modifier = Modifier.width(12.dp))
            }
        }
    }
}
