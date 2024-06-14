package com.jaegerapps.hansan.screens.onboarding.data.local.shared_preferences

interface LocalSharedPrefDataSource {
    suspend fun toggleOnboarding()
}