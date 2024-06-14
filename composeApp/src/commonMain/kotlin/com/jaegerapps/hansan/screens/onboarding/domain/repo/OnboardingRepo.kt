package com.jaegerapps.hansan.screens.onboarding.domain.repo

interface OnboardingRepo {
    suspend fun insertWords()
    suspend fun completeOnboarding()
}