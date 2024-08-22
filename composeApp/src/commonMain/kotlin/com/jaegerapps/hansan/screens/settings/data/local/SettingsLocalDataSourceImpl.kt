package com.jaegerapps.hansan.screens.settings.data.local

import com.jaegerapps.hansan.common.data.local.room.dao.GrammarDao
import com.jaegerapps.hansan.common.data.local.room.entity.GrammarEntity
import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.common.util.SettingKeys
import com.russhwolf.settings.Settings

class SettingsLocalDataSourceImpl(
    private val settings: Settings,
    private val grammarDao: GrammarDao,
) : SettingsLocalDataSource {
    override suspend fun getUserSettings(): UserSettings {


        val enableReminders = settings.getBoolean(SettingKeys.DAILY_REMINDERS_ENABLED, false)
        val dailyTargetMet = settings.getInt(SettingKeys.DAILY_TARGET_MET, 0)
        val dailyTargetMax = settings.getInt(SettingKeys.DAILY_TARGET_MAX, 50)
        return UserSettings(
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
        Knower.d("SettingsLocalDataSourceImpl", "Updating the daily target. ${settings.getIntOrNull(SettingKeys.DAILY_TARGET_MAX)}")
        return settings.getInt(SettingKeys.DAILY_TARGET_MAX, 50)
    }

    override suspend fun toggleFormality(
        formality: String,
        tenses: List<String>,
        isSelected: Boolean,
    ) {
        grammarDao.updateFormality(formality, tenses, isSelected)
    }

    override suspend fun toggleTense(
        tense: String,
        formalityList: List<String>,
        isSelected: Boolean,
    ) {
        grammarDao.updateTense(tense, formalityList, isSelected)
    }
    override suspend fun getAllGrammar(): List<GrammarEntity> {
        return grammarDao.getAllGrammar()
    }


}