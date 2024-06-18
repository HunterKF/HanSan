package com.jaegerapps.hansan.screens.settings.data.local

import com.jaegerapps.hansan.common.data.local.room.entity.GrammarEntity
import com.jaegerapps.hansan.common.models.UserSettings

interface SettingsLocalDataSource {
    suspend fun getUserSettings(): UserSettings
    suspend fun enableDailyReminders(value: Boolean): Boolean
    suspend fun updateDailyTarget(value: Int): Int
    suspend fun toggleFormality(formality: String, tenses: List<String>, isSelected: Boolean)
    suspend fun toggleTense(tense: String, formalityList: List<String>,isSelected: Boolean)
    suspend fun getEnabled(): List<GrammarEntity>
}