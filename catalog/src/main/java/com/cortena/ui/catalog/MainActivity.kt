package com.cortena.ui.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cortena.ui.catalog.demo.ButtonDemo
import com.cortena.ui.catalog.demo.ColorDemo
import com.cortena.ui.catalog.demo.SliderDemo
import com.cortena.ui.components.Text
import com.cortena.ui.layout.Body
import com.cortena.ui.layout.ContentView
import com.cortena.ui.layout.SafeArea
import com.cortena.ui.layout.ScrollView
import com.cortena.ui.theme.ThemeMode

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
                            verticalArrangement = Arrangement.spacedBy(16.dp),
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
