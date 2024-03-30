package com.jaegerapps.hansan.screens.practice.data.repo

import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.ModifierType
import com.russhwolf.settings.Settings
import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.common.models.formalityToString
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.stringToType
import com.jaegerapps.hansan.common.models.typeToString
import com.jaegerapps.hansan.common.util.SettingKeys
import com.jaegerapps.hansan.screens.practice.domain.repo.PracticeRepo

class PracticeRepoImpl(
    private val settings: Settings,
) : PracticeRepo {
    override suspend fun getUserSettings(): UserSettings {
        val formality = settings.getString(SettingKeys.FORMALITY, "formal_high")
        val type = settings.getString(SettingKeys.TYPE, "verb")
        val keyboardEnabled = settings.getBoolean(SettingKeys.KEYBOARD_ENABLED, false)
        val presentTenseEnabled = settings.getBoolean(SettingKeys.PRESENT_TENSE_ENABLED, true)
        val pastTenseEnabled = settings.getBoolean(SettingKeys.PAST_TENSE_ENABLED, true)
        val futureTenseEnabled = settings.getBoolean(SettingKeys.FUTURE_TENSE_ENABLED, true)
        val enableReminders = settings.getBoolean(SettingKeys.DAILY_REMINDERS_ENABLED, false)
        val dailyTargetMet = settings.getInt(SettingKeys.DAILY_TARGET_MET, 0)
        val dailyTargetMax = settings.getInt(SettingKeys.DAILY_TARGET_MAX, 50)
        return UserSettings(
            targetFormality = getFormalityFromString(formality),
            targetType = stringToType(type),
            keyboardEnabled = keyboardEnabled,
            presentTenseEnabled = presentTenseEnabled,
            pastTenseEnabled = pastTenseEnabled,
            futureTenseEnabled = futureTenseEnabled,
            enableReminders = enableReminders,
            dailyTargetMet = dailyTargetMet,
            dailyTargetMax = dailyTargetMax
        )
    }

    override suspend fun updateUserSettingsType(type: ModifierType) {
        settings.putString(SettingKeys.TYPE, typeToString(type))
    }

    override suspend fun updateUserSettingsFormality(formality: Formality) {
        settings.putString(SettingKeys.FORMALITY, formalityToString(formality))
    }


    override suspend fun updateUserSettingsKeyboard(enabled: Boolean): Boolean {
        settings.putBoolean(SettingKeys.KEYBOARD_ENABLED, enabled)
        return settings.getBoolean(SettingKeys.KEYBOARD_ENABLED, false)
    }

    override suspend fun updateDailyGoalMet(newValue: Int) {
        settings.putInt(SettingKeys.DAILY_TARGET_MET, newValue)
    }
}