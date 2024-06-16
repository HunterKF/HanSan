package com.jaegerapps.hansan.screens.settings.presentation

import com.jaegerapps.hansan.screens.settings.domain.models.SettingsFormalityModel
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsTenseModel

data class SettingsUiState(
    val enableReminders: Boolean = false,
    val dailyTarget: Int = 50,
    val loading: Boolean = false,
    val errorMessage: SettingsErrorMessage? = null,
    val tenses: List<SettingsTenseModel> = emptyList(),
    val formalities: List<SettingsFormalityModel> = emptyList(),
)

enum class SettingsErrorMessage {
    TENSE_BLANK,
    DAILY_BLANK
}
