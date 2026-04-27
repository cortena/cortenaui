package com.cortena.components.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cortena.components.catalog.shape.ShapeCatalog
import com.cortena.components.layout.AppBar
import com.cortena.components.layout.Body
import com.cortena.components.layout.ContentView
import com.cortena.components.layout.SafeArea
import com.cortena.components.theme.LocalColors
import com.cortena.components.theme.StatusBarIconMode
import com.cortena.components.ui.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ContentView(
            appBar = {
                val colors = LocalColors.current
                AppBar(modifier = Modifier.background(Color(colors.background))) {}
            },
            statusBarIconMode = StatusBarIconMode.Light,
        ) {
            val colors = LocalColors.current
            Body(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(colors.background))
            ) {
                SafeArea {
                    Column {
                        Text("Shape Catalog", modifier = Modifier.padding(bottom = 16.dp))
                        ShapeCatalog()
                    }
                }
            }
        }
    }
}
