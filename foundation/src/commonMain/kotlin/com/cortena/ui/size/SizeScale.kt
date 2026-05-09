/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026-present The CortenaOS Project
 */
package com.cortena.ui.size

/**
 * CortenaUI — Size Scale
 *
 * Raw Float constants (dp equivalent) for each [SizeToken] tier. Framework-agnostic — the Compose
 * layer wraps these as Dp. Medium values match every existing hardcoded dimension in the library,
 * so adopting the size system introduces zero visual regressions.
 */
object SizeScale {

    // Component height (dp) — used by Button, AppBar touch targets, etc.
    const val HeightExtraSmall: Float = 28f
    const val HeightSmall: Float = 36f
    const val HeightMedium: Float = 48f
    const val HeightLarge: Float = 56f
    const val HeightExtraLarge: Float = 64f

    // Horizontal padding (dp) — inner content padding for buttons and similar
    const val HorizontalPaddingExtraSmall: Float = 8f
    const val HorizontalPaddingSmall: Float = 12f
    const val HorizontalPaddingMedium: Float = 16f
    const val HorizontalPaddingLarge: Float = 20f
    const val HorizontalPaddingExtraLarge: Float = 24f

    // Icon size (dp) — default icon dimensions inside sized components
    const val IconSizeExtraSmall: Float = 14f
    const val IconSizeSmall: Float = 16f
    const val IconSizeMedium: Float = 20f
    const val IconSizeLarge: Float = 24f
    const val IconSizeExtraLarge: Float = 28f

    // Content gap (dp) — spacing between icon and label inside a component
    const val ContentGapExtraSmall: Float = 4f
    const val ContentGapSmall: Float = 6f
    const val ContentGapMedium: Float = 8f
    const val ContentGapLarge: Float = 10f
    const val ContentGapExtraLarge: Float = 12f

    // Toggle track width (dp)
    const val ToggleTrackWidthExtraSmall: Float = 40f
    const val ToggleTrackWidthSmall: Float = 52f
    const val ToggleTrackWidthMedium: Float = 64f
    const val ToggleTrackWidthLarge: Float = 76f
    const val ToggleTrackWidthExtraLarge: Float = 88f

    // Toggle track height (dp)
    const val ToggleTrackHeightExtraSmall: Float = 20f
    const val ToggleTrackHeightSmall: Float = 24f
    const val ToggleTrackHeightMedium: Float = 28f
    const val ToggleTrackHeightLarge: Float = 32f
    const val ToggleTrackHeightExtraLarge: Float = 36f

    // Toggle thumb padding (dp) — inset between track edge and thumb
    const val ToggleThumbPaddingExtraSmall: Float = 1.5f
    const val ToggleThumbPaddingSmall: Float = 2f
    const val ToggleThumbPaddingMedium: Float = 2f
    const val ToggleThumbPaddingLarge: Float = 2.5f
    const val ToggleThumbPaddingExtraLarge: Float = 3f

    // Toggle thumb width (dp) — sliding capsule width, deliberately narrower than the inner track
    const val ToggleThumbWidthExtraSmall: Float = 25f
    const val ToggleThumbWidthSmall: Float = 32f
    const val ToggleThumbWidthMedium: Float = 40f
    const val ToggleThumbWidthLarge: Float = 48f
    const val ToggleThumbWidthExtraLarge: Float = 56f

    // Slider indicator width (dp)
    const val SliderIndicatorWidthExtraSmall: Float = 32f
    const val SliderIndicatorWidthSmall: Float = 40f
    const val SliderIndicatorWidthMedium: Float = 48f
    const val SliderIndicatorWidthLarge: Float = 56f
    const val SliderIndicatorWidthExtraLarge: Float = 64f

    // Slider track height (dp) — track/progress bar visible height
    const val SliderTrackHeightExtraSmall: Float = 10f
    const val SliderTrackHeightSmall: Float = 14f
    const val SliderTrackHeightMedium: Float = 16f
    const val SliderTrackHeightLarge: Float = 20f
    const val SliderTrackHeightExtraLarge: Float = 24f
}
