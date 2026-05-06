package com.cortena.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cortena.ui.theme.LocalColors

@Composable
fun Body(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val colors = LocalColors.current
    Column(modifier = modifier.fillMaxSize().background(Color(colors.surface))) { content() }
}
