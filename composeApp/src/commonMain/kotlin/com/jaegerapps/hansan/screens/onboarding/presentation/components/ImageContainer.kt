package com.jaegerapps.hansan.screens.onboarding.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImageContainer(modifier: Modifier = Modifier, drawableResource: DrawableResource) {
    Image(
        modifier = modifier
            .fillMaxWidth(0.8f),
        painter = painterResource(drawableResource),
        contentDescription = null
    )
}