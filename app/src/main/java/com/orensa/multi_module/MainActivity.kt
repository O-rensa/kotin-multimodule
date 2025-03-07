package com.orensa.multi_module

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.orensa.multi_module.ui.theme.MultimoduleTheme
import com.orensa.network.Character
import com.orensa.network.KtorClient
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private val ktorClient = KtorClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var character by remember {
                mutableStateOf<Character?>(null)
            }

            LaunchedEffect(key1 = Unit, block = {
                delay(3000)
                character = ktorClient.getCharacter(1)
            })

            MultimoduleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column {
                        Greeting("Android")
                        Text(text = character?.name ?: "No Character")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(message: String, modifier: Modifier = Modifier) {
    Text(
        text = message,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MultimoduleTheme {
        Greeting("Android")
    }
}