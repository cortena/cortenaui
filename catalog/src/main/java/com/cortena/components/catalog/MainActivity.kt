package com.cortena.components.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cortena.components.layout.AppBar
import com.cortena.components.layout.Body
import com.cortena.components.layout.ContentView
import com.cortena.components.layout.SafeArea
import com.cortena.components.theme.StatusBarIconMode
import com.cortena.components.typography.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ContentView(
            appBar = { AppBar(modifier = Modifier.background(Color.Red)) {} },
            statusBarColor = Color.Green,
            statusBarIconMode = StatusBarIconMode.Dark,
        ) {
            Body(modifier = Modifier.background(Color.Blue)) {
                SafeArea { Text(text = "Hello there! from Cortena Compose!") }
            }
        }
    }
}
