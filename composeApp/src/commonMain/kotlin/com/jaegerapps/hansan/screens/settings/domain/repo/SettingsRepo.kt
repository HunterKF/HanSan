package com.jaegerapps.hansan.screens.settings.domain.repo

import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsFormalityModel
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsTenseModel

interface SettingsRepo {
    suspend fun getUserSettings(): UserSettings
    suspend fun enableDailyReminders(value: Boolean): Boolean
    suspend fun updateDailyTarget(value: Int): Int
    suspend fun toggleFormality(formalityType: FormalityType, tenses: List<SettingsTenseModel>, isSelected: Boolean)
    suspend fun toggleTense(
        tense: Tense,
        formalities: List<SettingsFormalityModel>,
        isSelected: Boolean,
    )

    suspend fun getEnabled(): Pair<List<SettingsFormalityModel>, List<SettingsTenseModel>>

}