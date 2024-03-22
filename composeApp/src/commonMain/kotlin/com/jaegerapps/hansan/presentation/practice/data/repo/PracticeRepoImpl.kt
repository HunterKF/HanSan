package com.jaegerapps.hansan.presentation.practice.data.repo

import com.russhwolf.settings.Settings
import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.common.models.formalityToString
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.stringToType
import com.jaegerapps.hansan.common.models.typeToString
import com.jaegerapps.hansan.common.util.SettingKeys
import com.jaegerapps.hansan.presentation.practice.domain.repo.PracticeRepo

class PracticeRepoImpl(
    private val settings: Settings,
) : PracticeRepo {
    override suspend fun getUserSettings(): UserSettings {
        val formality = settings.getString(SettingKeys.FORMALITY, "formal_high")
        val type = settings.getString(SettingKeys.TYPE, "verb")
        settings.getBoolean(SettingKeys.KEYBOARD_ENABLED, false)
        return UserSettings(
            targetFormality = getFormalityFromString(formality),
            targetType = stringToType(type),
            keyboardEnabled = settings.getBoolean(SettingKeys.KEYBOARD_ENABLED, false)
        )
    }

    override suspend fun updateUserSettingsTypeForm(userSettings: UserSettings): UserSettings {

        settings.putString(SettingKeys.FORMALITY, formalityToString(userSettings.targetFormality))
        settings.putString(SettingKeys.TYPE, typeToString(userSettings.targetType))

        val formality = settings.getString(SettingKeys.FORMALITY, "formal_high")
        val type = settings.getString(SettingKeys.TYPE, "verb")
        settings.getBoolean(SettingKeys.KEYBOARD_ENABLED, false)
        return UserSettings(
            targetFormality = getFormalityFromString(formality),
            targetType = stringToType(type),
            keyboardEnabled = settings.getBoolean(SettingKeys.KEYBOARD_ENABLED, false)
        )
    }

    override suspend fun updateUserSettingsKeyboard(enabled: Boolean): Boolean {
        settings.putBoolean(SettingKeys.KEYBOARD_ENABLED, enabled)
        return settings.getBoolean(SettingKeys.KEYBOARD_ENABLED, false)
    }
}