package com.jaegerapps.hansan.screens.settings.domain.repo

import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.DetailedTense
import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsFormalityModel
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsTenseModel

interface SettingsRepo {
    suspend fun getUserSettings(): UserSettings
    suspend fun enableDailyReminders(value: Boolean): Boolean
    suspend fun updateDailyTarget(value: Int): Int
    suspend fun toggleFormality(formalityType: FormalityType, tenses: List<SettingsTenseModel>, isSelected: Boolean)
    suspend fun toggleTense(
        detailedTense: DetailedTense,
        formalities: List<SettingsFormalityModel>,
        isSelected: Boolean,
    )

    suspend fun getGrammar(): Pair<List<SettingsFormalityModel>, List<SettingsTenseModel>>

}