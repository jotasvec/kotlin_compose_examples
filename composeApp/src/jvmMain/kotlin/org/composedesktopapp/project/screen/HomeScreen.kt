package org.composedesktopapp.project.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import org.composedesktopapp.project.api.APIService
import java.util.UUID

class HomeScreen : Screen {
    @Composable
    override fun Content(){
        val navigator = LocalNavigator.currentOrThrow

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Button(onClick = {
                    navigator.push(
                        DetailsScreen(UUID.randomUUID().toString())
                    )
                }){
                    Text("Details")
                }
                Button(onClick = {
                    navigator.push(
                        ExampleScreen(UUID.randomUUID().toString())
                    )
                }){
                    Text("Examples")
                }
                //Open Another Window
                SecondWindow()
                Button(onClick = {
                    navigator.push(
                    DrawingApp()
                    )
                }){
                    Text("Canvas")
                }

            }
        }

    }

}

@Composable
private fun SecondWindow() {
    var secondWindow by remember { mutableStateOf(false) }
    var apiService by remember { mutableStateOf("Waiting ...") }
    val scope = rememberCoroutineScope()

    // Added by myself to apply a second window
    Button(onClick = {secondWindow = true}) {
        Text("Open")
    }
    if(secondWindow) {

        Window(
            onCloseRequest = {},
            title="New Windows",
            state = WindowState(width = 300.dp, height = 600.dp),
            resizable = false

        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(onClick = {
                    scope.launch {
                        apiService = APIService().fetchData()
                    }
                }){
                    Text("Get Data")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = apiService)

                Button(onClick = { secondWindow = false  }) {
                    Text("Close")
                }

            }
        }
    }
}