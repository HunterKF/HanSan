package com.jaegerapps.hansan.screens.onboarding.presentation

import com.jaegerapps.hansan.screens.onboarding.presentation.components.OnboardingScreens

data class OnboardingUiState(
    val currentScreen: OnboardingScreens = OnboardingScreens.WELCOME_SCREEN,
    val isLoading: Boolean = false
)