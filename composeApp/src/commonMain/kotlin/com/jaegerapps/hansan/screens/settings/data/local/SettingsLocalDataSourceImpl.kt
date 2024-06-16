package com.jaegerapps.hansan.screens.settings.data.local

import com.jaegerapps.hansan.common.data.local.room.dao.GrammarDao
import com.jaegerapps.hansan.common.data.local.room.entity.GrammarEntity
import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.getTenseFromString
import com.jaegerapps.hansan.common.models.stringToType
import com.jaegerapps.hansan.common.use_case.SettingsStringUseCase
import com.jaegerapps.hansan.common.util.SettingKeys
import com.russhwolf.settings.Settings

class SettingsLocalDataSourceImpl(
    private val settings: Settings,
    private val grammarDao: GrammarDao,
) : SettingsLocalDataSource {
    override suspend fun getUserSettings(): UserSettings {
        val enabledFormalities = SettingsStringUseCase.convertToList(
            settings.getString(
                SettingKeys.FORMALITIES,
                "formal_high"
            )
        )
        val enabledTenses = SettingsStringUseCase.convertToList(
            settings.getString(
                SettingKeys.TENSES,
                "present_declarative"
            )
        )

        val enableReminders = settings.getBoolean(SettingKeys.DAILY_REMINDERS_ENABLED, false)
        val dailyTargetMet = settings.getInt(SettingKeys.DAILY_TARGET_MET, 0)
        val dailyTargetMax = settings.getInt(SettingKeys.DAILY_TARGET_MAX, 50)
        return UserSettings(
            enabledFormality = enabledFormalities.map { getFormalityFromString(it) },
            enabledTenses = enabledTenses.map { getTenseFromString(it) },
            enableReminders = enableReminders,
            currentPracticeDone = dailyTargetMet,
            dailyTargetMax = dailyTargetMax
        )
    }

    override suspend fun enableDailyReminders(value: Boolean): Boolean {
        settings.putBoolean(SettingKeys.DAILY_REMINDERS_ENABLED, value)
        return settings.getBoolean(SettingKeys.DAILY_REMINDERS_ENABLED, false)
    }

    override suspend fun updateDailyTarget(value: Int): Int {
        settings.putInt(SettingKeys.DAILY_TARGET_MAX, value)
        return settings.getInt(SettingKeys.DAILY_TARGET_MAX, 50)
    }

    override suspend fun toggleFormality(formality: String, isSelected: Boolean) {
        grammarDao.updateFormality(formality, isSelected)
    }

    override suspend fun toggleTense(tense: String, isSelected: Boolean) {
        grammarDao.updateTense(tense, isSelected)
    }

    override suspend fun getEnabled(): List<GrammarEntity> {
        return grammarDao.getSelectedGrammar()
    }


}