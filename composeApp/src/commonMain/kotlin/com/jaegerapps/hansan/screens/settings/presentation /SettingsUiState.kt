package com.jaegerapps.hansan.screens.settings.presentation

data class SettingsUiState(
    val presentTenseEnabled: Boolean = true,
    val pastTenseEnabled: Boolean= true,
    val futureTenseEnabled: Boolean = true,
    val enableReminders: Boolean = false,
    val dailyTarget: Int = 50,
    val loading: Boolean = false,
    val errorMessage: SettingsErrorMessage? = null
)

enum class SettingsErrorMessage {
    TENSE_BLANK,
    DAILY_BLANK
}
