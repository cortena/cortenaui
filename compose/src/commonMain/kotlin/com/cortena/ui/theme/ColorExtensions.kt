package com.cortena.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.cortena.ui.color.AdaptiveColor

// Auto-resolve an adaptive color to the correct compose color for the active theme
@Composable fun AdaptiveColor.value(): Color = Color(resolve(LocalIsDark.current))
