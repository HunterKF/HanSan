package com.jaegerapps.hansan.screens.onboarding.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    contentDescription: String?,
    imageVector: ImageVector,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier.clip(CircleShape)
            .background(MaterialTheme.colorScheme.secondary)
            .clickable { onClick() }.padding(12.dp),
    ) {
        Icon(

            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}