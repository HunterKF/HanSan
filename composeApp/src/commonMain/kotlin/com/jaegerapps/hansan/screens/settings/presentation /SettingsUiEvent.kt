package com.jaegerapps.hansan.screens.settings.presentation

sealed interface SettingsUiEvent {
    data class TogglePresentTense(val value: Boolean): SettingsUiEvent
    data class TogglePastTense(val value: Boolean): SettingsUiEvent
    data class ToggleFutureTense(val value: Boolean): SettingsUiEvent
    data class ToggleDailyReminders(val value: Boolean): SettingsUiEvent
    data class ChangeDailyTarget(val number: String): SettingsUiEvent
    data class OnNavigate(val route: String): SettingsUiEvent
    data object ClearErrorMessage: SettingsUiEvent
}