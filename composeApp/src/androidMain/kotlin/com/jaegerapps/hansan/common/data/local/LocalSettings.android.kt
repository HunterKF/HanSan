package com.jaegerapps.hansan.common.data.local

import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.common.util.SettingKeys
import com.jaegerapps.hansan.screens.practice.presentation.DailyGoal
import com.russhwolf.settings.Settings

actual class LocalSettings(private val settings: Settings) {
    actual fun resetDailyGoals() {
        settings.putInt(SettingKeys.DAILY_TARGET_MET, 0)
    }
}