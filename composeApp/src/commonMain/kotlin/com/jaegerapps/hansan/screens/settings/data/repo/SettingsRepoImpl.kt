package com.jaegerapps.hansan.screens.settings.data.repo

import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.common.models.getStringFromFormality
import com.jaegerapps.hansan.common.models.getStringFromTense
import com.jaegerapps.hansan.screens.settings.data.local.SettingsLocalDataSource
import com.jaegerapps.hansan.screens.settings.domain.mapper.convertGrammarEntities
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsFormalityModel
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsTenseModel
import com.jaegerapps.hansan.screens.settings.domain.repo.SettingsRepo

class SettingsRepoImpl(
    private val local: SettingsLocalDataSource,
) : SettingsRepo {
    override suspend fun getUserSettings(): UserSettings {
        return local.getUserSettings()
    }

    override suspend fun enableDailyReminders(value: Boolean): Boolean {
        return local.enableDailyReminders(value)
    }

    override suspend fun updateDailyTarget(value: Int): Int {
        return local.updateDailyTarget(value)
    }

    override suspend fun toggleFormality(formalityType: FormalityType, tenses: List<SettingsTenseModel>, isSelected: Boolean) {
        local.toggleFormality(formality = getStringFromFormality(formalityType), tenses.map { getStringFromTense(it.tense) }, isSelected)
    }

    override suspend fun toggleTense(
        tense: Tense,
        formalities: List<SettingsFormalityModel>,
        isSelected: Boolean,
    ) {
        local.toggleTense(
            tense = getStringFromTense(tense),
            formalityList = formalities.map { getStringFromFormality(it.formalityType) },
            isSelected = isSelected,
        )
    }

    override suspend fun getEnabled(): Pair<List<SettingsFormalityModel>, List<SettingsTenseModel>> {
        return convertGrammarEntities(local.getEnabled())
    }

}