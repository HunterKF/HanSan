package com.jaegerapps.hansan.screens.settings.presentation

import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.Tense

sealed interface SettingsUiEvent {
    data class ToggleTense(val value: Boolean, val tense: Tense) : SettingsUiEvent
    data class ToggleFormality(val value: Boolean, val formality: FormalityType) : SettingsUiEvent
    data class ToggleDailyReminders(val value: Boolean) : SettingsUiEvent
    data class ChangeDailyTarget(val number: String) : SettingsUiEvent
    data class OnNavigate(val route: String) : SettingsUiEvent
    data object ClearErrorMessage : SettingsUiEvent
}