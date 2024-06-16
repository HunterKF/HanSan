package com.jaegerapps.hansan.screens.settings.data.repo

import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.common.models.getStringFromFormality
import com.jaegerapps.hansan.common.models.getStringFromTense
import com.jaegerapps.hansan.common.models.getTenseFromString
import com.jaegerapps.hansan.screens.settings.data.local.SettingsLocalDataSource
import com.jaegerapps.hansan.screens.settings.domain.mapper.toSettingsGrammarModel
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsGrammarModel
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

    override suspend fun toggleFormality(formalityType: FormalityType, isSelected: Boolean) {
        local.toggleFormality(formality = getStringFromFormality(formalityType), isSelected)
    }

    override suspend fun toggleTense(tense: Tense, isSelected: Boolean) {
        local.toggleTense(getStringFromTense(tense), isSelected)
    }

    override suspend fun getEnabled(): List<SettingsGrammarModel> {
       return local.getEnabled().map { it.toSettingsGrammarModel() }
    }

}