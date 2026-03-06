package org.composedesktopapp.project.screen

import androidx.compose.foundation.ContextMenuDataProvider
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import composedesktopapp.composeapp.generated.resources.Res
import composedesktopapp.composeapp.generated.resources.Visibility
import composedesktopapp.composeapp.generated.resources.VisibilityOff

data class DetailsScreen(val id: String) : Screen {
    @Preview
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        MaterialTheme{
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Row(modifier = Modifier.fillMaxWidth()
                    .height(60.dp)
                    .padding(0.dp),
                    verticalAlignment = Alignment.Top,
                ) {
                    IconButton(
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            navigator.pop()
                        }
                    )
                }
                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    //ContexMenuContent()
                    KeyEventContent(navigator)

                }
            }
        }
    }
}


@Composable
fun KeyEventContent(navigator: Navigator){
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val buttonEnabled = name.isNotEmpty() && lastName.isNotEmpty() && username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("name") }
        )
        Spacer8()
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName  = it },
            label = { Text("lastname") }
            // modifier = Modifier.onPreviewKeyEvent()
        )
        Spacer8()
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("username") }
        )
        Spacer8()
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("email") }
        )
        Spacer8()
        // password
        var passwordVisibility by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("password") },
            visualTransformation = if(!passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconToggleButton(checked = passwordVisibility, onCheckedChange = { passwordVisibility = !passwordVisibility }){
                    Icon( painter = painterResource( if(passwordVisibility) Res.drawable.Visibility else Res.drawable.VisibilityOff) ,
                        contentDescription = "Visibility"
                    )
                }
            }

        )

        Spacer8()
        Button(
            enabled = buttonEnabled,
            onClick = {
                navigator.push(ProfileScreen(username, name, lastName))
                println("user: $name $lastName \nemail: $email \nusername: $username ")
                name = ""
                email = ""
                password = ""
                lastName = ""
                username = ""

            }){
            Text("SignUp")
        }


    }

}

@Composable
fun Spacer8(){
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun ContexMenuContent(){
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ContextMenuDataProvider(
            items = {
                listOf(
                    ContextMenuItem(label = "Custom Action") { println("Custom Action Clicked!!!") },
                    ContextMenuItem(label = "Another Action") { println("Another Action Clicked!!!") },

                )
            }

        ){
            TextField ( value =  text, onValueChange = { text = it })
            Spacer(modifier = Modifier.height(10.dp))
            SelectionContainer {
                Text(modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
                    text = text,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

