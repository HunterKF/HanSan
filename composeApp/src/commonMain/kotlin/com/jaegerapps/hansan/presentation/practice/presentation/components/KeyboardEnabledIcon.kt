package com.jaegerapps.hansan.presentation.practice.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources.icon_keyboard
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun KeyboardEnabledIcon(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit
) {
    /*This is placed on the practice screen.
    * If keyboard is enabled, then the color is solid black, else gray*/
    val color by animateColorAsState(
        if (enabled) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.tertiary,
        tween(200)
    )
    IconButton(
        modifier = modifier,
        onClick = {
            onClick()
        }
    ) {
        Icon(
            painter = painterResource(Res.drawable.icon_keyboard),
            tint = color,
            contentDescription = null
        )
    }
}