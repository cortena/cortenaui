package com.cortena.ui.catalog.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cortena.ui.components.Text
import com.cortena.ui.layout.ScrollOrientation
import com.cortena.ui.layout.ScrollView
import kotlin.random.Random

@Composable
fun ScrollViewDemo() {
    val items1 = remember { List(50) { "Item ${Random.nextInt(1000)}" } }
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Nested Scroll Vertical")
        Row {
            ScrollView(modifier = Modifier.height(200.dp).weight(1f)) {
                items1.forEach { item -> Text(text = item) }
            }
            ScrollView(modifier = Modifier.height(200.dp).weight(1f)) {
                items1.forEach { item -> Text(text = item) }
            }
        }
        Text("Nested Scroll Horizontal")
        ScrollView(modifier = Modifier.height(100.dp), orientation = ScrollOrientation.Horizontal) {
            items1.forEach { item -> Text(text = item) }
        }
    }
}
