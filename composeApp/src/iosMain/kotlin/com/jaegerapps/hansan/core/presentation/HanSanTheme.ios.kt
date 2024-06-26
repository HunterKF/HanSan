package com.jaegerapps.hansan.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.jaegerapps.hansan.ui.theme.DarkColorScheme
import com.jaegerapps.hansan.ui.theme.LightColorScheme
import com.jaegerapps.hansan.ui.theme.getTypography

@Composable
actual fun HanSanTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme,
        typography = getTypography(),
        content = content
    )
}