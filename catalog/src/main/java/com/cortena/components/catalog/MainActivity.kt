package com.cortena.components.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.cortena.components.ContentView
import com.cortena.components.ui.AppBar
import com.cortena.components.ui.Body
import com.cortena.components.ui.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ContentView {
            AppBar {}
            Body {
                Text(text = "Hello there! from Cortena Lib!")
            }
        }
    }
}
