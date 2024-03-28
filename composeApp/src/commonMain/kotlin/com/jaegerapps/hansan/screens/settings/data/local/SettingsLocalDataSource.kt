package com.jaegerapps.hansan.screens.settings.data.local

import com.jaegerapps.hansan.common.models.UserSettings

interface SettingsLocalDataSource {
    suspend fun getUserSettings(): UserSettings
    suspend fun enableDailyReminders(value: Boolean): Boolean
    suspend fun updateDailyTarget(value: Int): Int
    suspend fun updatePresentTense(value: Boolean): Boolean
    suspend fun updatePastTense(value: Boolean): Boolean
    suspend fun updateFutureTense(value: Boolean): Boolean
}