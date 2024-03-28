package com.jaegerapps.hansan.screens.settings.domain.repo

import com.jaegerapps.hansan.common.models.UserSettings

interface SettingsRepo {
    suspend fun getUserSettings(): UserSettings
    suspend fun enableDailyReminders(value: Boolean): Boolean
    suspend fun updateDailyTarget(value: Int) : Int
    suspend fun updatePresentTense(value: Boolean): Boolean
    suspend fun updatePastTense(value: Boolean): Boolean
    suspend fun updateFutureTense(value: Boolean): Boolean
}