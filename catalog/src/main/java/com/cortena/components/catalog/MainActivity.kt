package com.cortena.components.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cortena.components.catalog.demo.ButtonDemo
import com.cortena.components.catalog.demo.ColorDemo
import com.cortena.components.catalog.demo.SliderDemo
import com.cortena.components.layout.Body
import com.cortena.components.layout.ContentView
import com.cortena.components.layout.SafeArea
import com.cortena.components.layout.ScrollView
import com.cortena.components.theme.ThemeMode
import com.cortena.components.ui.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val themeMode = mutableStateOf(ThemeMode.Light)

        ContentView(themeMode = { themeMode.value }) {
            Body {
                ScrollView {
                    SafeArea {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                "Long click to switch ThemeMode",
                                modifier = Modifier.padding(top = 16.dp),
                            )
                            ButtonDemo(themeMode)
                            SliderDemo()
                            ColorDemo()
                        }
                    }
                }
            }
        }
    }
}
