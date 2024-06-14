package com.jaegerapps.hansan.screens.onboarding.data.local.shared_preferences

import com.jaegerapps.hansan.common.util.SettingKeys.ONBOARDING
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class LocalSharedPrefDataSourceImpl(
    private val settings: Settings
): LocalSharedPrefDataSource {
    override suspend fun toggleOnboarding() {
        settings[ONBOARDING] = false
    }
}