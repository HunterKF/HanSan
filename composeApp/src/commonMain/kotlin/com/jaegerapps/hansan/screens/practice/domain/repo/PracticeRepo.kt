package com.jaegerapps.hansan.screens.practice.domain.repo

import com.jaegerapps.hansan.common.models.UserSettings

interface PracticeRepo {
    suspend fun getUserSettings(): UserSettings
    suspend fun updateUserSettingsTypeForm(userSettings: UserSettings): UserSettings
    suspend fun updateUserSettingsKeyboard(enabled: Boolean): Boolean
}