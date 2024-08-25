package com.jaegerapps.hansan.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val DarkColorScheme = darkColorScheme(
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surfaceContainer = surfaceContainerColorDark,
    onSurface = onSurfaceDark,
    secondary = secondaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = Color.White,
    onPrimary = onPrimaryDark,
)

val LightColorScheme = lightColorScheme(
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surfaceContainer = surfaceContainerColorLight,
    onSurface = onSurfaceLight,
    secondary = secondaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = Color.White,
    onPrimary = onPrimaryLight

)