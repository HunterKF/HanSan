package com.jaegerapps.hansan.screens.onboarding.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaegerapps.hansan.screens.onboarding.presentation.components.CircleButton
import com.jaegerapps.hansan.screens.onboarding.presentation.components.ImageContainer
import com.jaegerapps.hansan.screens.onboarding.presentation.components.MessageContainer
import com.jaegerapps.hansan.screens.onboarding.presentation.components.OnboardingScreens
import com.jaegerapps.hansan.screens.onboarding.presentation.components.OnboardingStrings
import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources._785_generated_transparent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun OnboardingScreen(state: OnboardingUiState, onComplete: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ImageContainer(
            modifier = Modifier.weight(1f),
            drawableResource = OnboardingStrings.getImage(state.currentScreen)
        )
        MessageContainer(
            modifier = Modifier.fillMaxWidth().weight(0.5f),
            title = stringResource(OnboardingStrings.getTitle(state.currentScreen)),
            message = stringResource(OnboardingStrings.getText(state.currentScreen))
        )
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            CircleButton(
                contentDescription = null,
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                onClick = {
                    onComplete()
                }
            )
        }
        Spacer(Modifier.height(32.dp))
    }
}