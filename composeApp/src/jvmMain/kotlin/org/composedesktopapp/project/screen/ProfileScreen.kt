package org.composedesktopapp.project.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.composedesktopapp.project.DarkSurface
import org.composedesktopapp.project.Purple40
import org.composedesktopapp.project.Purple80
import org.composedesktopapp.project.PurpleGrey40
import org.composedesktopapp.project.PurpleGrey80

class ProfileScreen(val username : String, val name: String, val lastName: String ) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Column {
            IconButton(
                content = { Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null) },
                onClick = {
                navigator.pop()
            })
            Spacer8()
            Box(Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
                ) {

                ProfileCard(name, lastName, username)
            }
        }


    }

}

@Composable
fun StatItem(value: String, label: String){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Purple80, modifier = Modifier.padding(8.dp))
        Spacer8()
        Text(label, fontSize = 13.sp, color = PurpleGrey80, modifier = Modifier.padding(8.dp))

    }
}
@Preview(showBackground = true)
@Composable
fun ProfileCard(name: String = "Jota", lastName: String = "Svec", username: String = "jotasvec") {
    Card(
        modifier = Modifier.width(380.dp).padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        colors  = CardDefaults.cardColors(contentColor = DarkSurface),
        elevation = CardDefaults.cardElevation(8.dp)

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp).fillMaxWidth()
        ) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.clip(CircleShape).background(Purple40).size(96.dp),
            ) {
                Text(
                    text = name[0].toString() + lastName[0].toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                )
            }
            Spacer(Modifier.height(16.dp))
            Text(
                text = username,
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
            Spacer8()
            Text("Android Developer", fontSize = 16.sp, color = PurpleGrey80)
            Spacer(Modifier.height(20.dp))
            Divider(color = PurpleGrey40.copy(alpha = 0.3f), thickness = 1.dp)
            Spacer(Modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                StatItem("128", "Posts")
                StatItem("14K", "Followers")
                StatItem("920", "Following")
            }
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Building beautiful Desktop apps with JetPack Compose",
                fontSize = 14.sp,
                color = PurpleGrey80,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
            )
            Spacer(Modifier.height(20.dp))
            Divider(color = PurpleGrey80, thickness = 1.dp)
            Button(onClick = {},
                modifier = Modifier.fillMaxWidth().height(48.dp),
                content = { Text("Follow Up", color = PurpleGrey80, fontSize = 14.sp, fontWeight = FontWeight.SemiBold) }
            )
        }
    }
}