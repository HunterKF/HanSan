package com.jaegerapps.hansan.screens.practice.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaegerapps.hansan.screens.practice.domain.models.AnswerResponse
import kotlinx.coroutines.delay

@Composable
fun WordContainer(
    modifier: Modifier = Modifier,
    word: String,
    definition: String,
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = definition,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = word,
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onBackground

        )
    }

}

@Composable
fun FadeAnimation(
    modifier: Modifier,
    visible: Boolean,
    initialAlpha: Float = 0.0f,
    animationDuration: Int = 3000,
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(
            initialAlpha = initialAlpha,
            animationSpec = tween(durationMillis = animationDuration)
        ) + scaleIn(
            initialScale = 0.5f
        ),
        exit = fadeOut(
            targetAlpha = initialAlpha,
            animationSpec = tween(durationMillis = animationDuration)
        ) + scaleOut(
            targetScale = 0.5f
        )
    ) {
        content()
    }
}