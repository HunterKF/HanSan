package com.jaegerapps.hansan.screens.settings.data.repo

import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.screens.settings.data.local.SettingsLocalDataSource
import com.jaegerapps.hansan.screens.settings.domain.repo.SettingsRepo

class SettingsRepoImpl(
    private val local: SettingsLocalDataSource
): SettingsRepo {
    override suspend fun getUserSettings(): UserSettings {
        return local.getUserSettings()
    }

    override suspend fun enableDailyReminders(value: Boolean): Boolean {
        return local.enableDailyReminders(value)
    }

    override suspend fun updateDailyTarget(value: Int): Int {
        return local.updateDailyTarget(value)
    }

    override suspend fun updatePresentTense(value: Boolean): Boolean {
       return local.updatePresentTense(value)
    }

    override suspend fun updatePastTense(value: Boolean): Boolean {
        return local.updatePastTense(value)
    }

    override suspend fun updateFutureTense(value: Boolean): Boolean {
        return local.updateFutureTense(value)
    }

}