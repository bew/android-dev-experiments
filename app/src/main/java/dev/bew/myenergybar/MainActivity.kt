// WHAT:
// The single Activity — the OS-level entry point of the app.
// Sets up Compose as the UI layer via setContent{} and renders the root UI.
//
// NEEDED: Yes — required entry point.
// Every Android app needs at least one Activity declared as the launcher in `AndroidManifest.xml`.

package dev.bew.myenergybar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.bew.myenergybar.ui.theme.MyEnergyBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyEnergyBarTheme {
                HelloWorld()
            }
        }
    }
}

@Composable
fun HelloWorld(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(Icons.Default.Favorite, contentDescription = null)
        Text("Hello, World!")
        Text("This seems to be working well! 🚀")
    }
}

@Preview(showBackground = true)
@Composable
fun HelloWorldPreview() {
    MyEnergyBarTheme {
        HelloWorld()
    }
}
