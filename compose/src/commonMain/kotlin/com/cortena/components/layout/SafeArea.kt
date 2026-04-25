package com.cortena.components.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cortena.components.theme.LocalSpacing

@Composable
fun SafeArea(
    modifier: Modifier = Modifier,
    horizontal: Dp = LocalSpacing.current.Md.dp, // 16dp
    vertical: Dp = LocalSpacing.current.None.dp, // 0dp
    content: @Composable () -> Unit,
) {
    Box(modifier = modifier.padding(horizontal = horizontal, vertical = vertical)) { content() }
}
