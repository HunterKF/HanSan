package com.jaegerapps.hansan.screens.settings.data.local

import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.stringToType
import com.jaegerapps.hansan.common.util.SettingKeys
import com.russhwolf.settings.Settings

class SettingsLocalDataSourceImpl(val settings: Settings) : SettingsLocalDataSource {
    override suspend fun getUserSettings(): UserSettings {
        val formality = settings.getString(SettingKeys.FORMALITY, "formal_high")
        val type = settings.getString(SettingKeys.TYPE, "verb")
        val keyboardEnabled = settings.getBoolean(SettingKeys.KEYBOARD_ENABLED, false)
        val presentTenseEnabled = settings.getBoolean(SettingKeys.PRESENT_TENSE_ENABLED, true)
        val pastTenseEnabled = settings.getBoolean(SettingKeys.PAST_TENSE_ENABLED, true)
        val futureTenseEnabled = settings.getBoolean(SettingKeys.FUTURE_TENSE_ENABLED, true)
        val enableReminders = settings.getBoolean(SettingKeys.DAILY_REMINDERS_ENABLED, false)
        val dailyTargetMet = settings.getInt(SettingKeys.DAILY_TARGET_MET, 50)
        val dailyTargetMax = settings.getInt(SettingKeys.DAILY_TARGET_MAX, 50)
        return UserSettings(
            targetFormality = getFormalityFromString(formality),
            targetType = stringToType(type),
            keyboardEnabled = keyboardEnabled,
            presentTenseEnabled = presentTenseEnabled,
            pastTenseEnabled = pastTenseEnabled,
            futureTenseEnabled = futureTenseEnabled,
            enableReminders = enableReminders,
            dailyTargetMax = dailyTargetMax,
            dailyTargetMet = dailyTargetMet
        )
    }

    override suspend fun enableDailyReminders(value: Boolean): Boolean {
        settings.putBoolean(SettingKeys.DAILY_REMINDERS_ENABLED, value)
        return settings.getBoolean(SettingKeys.DAILY_REMINDERS_ENABLED, false)
    }

    override suspend fun updateDailyTarget(value: Int): Int {
        settings.putInt(SettingKeys.DAILY_TARGET_MAX, value)
        return settings.getInt(SettingKeys.DAILY_TARGET_MAX, 50)
    }

    override suspend fun updatePresentTense(value: Boolean): Boolean {
        settings.putBoolean(SettingKeys.PRESENT_TENSE_ENABLED, value)
        return settings.getBoolean(SettingKeys.PRESENT_TENSE_ENABLED, true)
    }

    override suspend fun updatePastTense(value: Boolean): Boolean {
        settings.putBoolean(SettingKeys.PAST_TENSE_ENABLED, value)
        return settings.getBoolean(SettingKeys.PAST_TENSE_ENABLED, true)
    }

    override suspend fun updateFutureTense(value: Boolean): Boolean {
        settings.putBoolean(SettingKeys.FUTURE_TENSE_ENABLED, value)
        return settings.getBoolean(SettingKeys.FUTURE_TENSE_ENABLED, true)
    }
}