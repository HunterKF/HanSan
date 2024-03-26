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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaegerapps.hansan.screens.practice.domain.models.AnswerResponse
import kotlinx.coroutines.delay

@Composable
fun WordContainer(
    modifier: Modifier = Modifier,
    answerResponse: AnswerResponse?,
    word: String,
    definition: String,
    onEvent: () -> Unit
) {
    var itemVisible by remember { mutableStateOf(false) }
    LaunchedEffect(
        key1 = word,
        block = {
            // Animate the item when it changes or first appears
            itemVisible = false
            delay(200) // Delay for visibility change to take effect
            itemVisible = true
        }
    )
    var wrongAnswer by remember { mutableStateOf(false) }
    LaunchedEffect(answerResponse) {
        if (answerResponse == AnswerResponse.WRONG) {
            println("Response started")
            wrongAnswer = true
            delay(375) // Delay for visibility change to take effect
            println("Response ended")
            wrongAnswer = false
            onEvent()
        }
    }
    val animateOffset by animateDpAsState(
        targetValue = if (wrongAnswer) 6.dp else 0.dp,
        animationSpec = spring(Spring.DampingRatioHighBouncy, Spring.StiffnessLow)
    )
    FadeAnimation(
        modifier = modifier,
        visible = itemVisible,
        content = {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.offset(x = animateOffset),
                    text = word,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = 64.sp
                    )
                )
                Text(
                    text = definition,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        },
        animationDuration = 500
    )

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