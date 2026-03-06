package org.composedesktopapp.project


import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import composedesktopapp.composeapp.generated.resources.DarkMode
import composedesktopapp.composeapp.generated.resources.LightMode
import composedesktopapp.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import org.composedesktopapp.project.screen.HomeScreen


@Preview
@Composable
fun App() {
    var isDarkTheme by remember { mutableStateOf(true) }

    AppTheme(darkTheme = isDarkTheme) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                // Change Theme

                IconToggleButton(
                    checked = isDarkTheme,
                    onCheckedChange = { isDarkTheme = !isDarkTheme },
                    modifier = Modifier.align(Alignment.TopEnd).padding(16.dp),
                    ){
                    Icon(painter = painterResource(if(isDarkTheme) Res.drawable.LightMode else Res.drawable.DarkMode ), contentDescription = "DarkLightMode")
                }
                // == Navigation Example ==
                Navigator(HomeScreen()){
                        navigator -> SlideTransition(navigator)
                }
            }
        }
    }

        // == First examples ==
        /*var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }*/



}