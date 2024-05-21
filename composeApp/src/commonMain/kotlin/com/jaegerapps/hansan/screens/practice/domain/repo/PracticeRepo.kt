package com.jaegerapps.hansan.screens.practice.domain.repo

import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.ModifierType
import com.jaegerapps.hansan.common.models.UserSettings

interface PracticeRepo {
    suspend fun getUserSettings(): UserSettings
    suspend fun updateUserSettingsType(type: ModifierType)
    suspend fun updateUserSettingsFormality(formalityType: FormalityType)
    suspend fun updateUserSettingsKeyboard(enabled: Boolean): Boolean
    suspend fun updateDailyGoalMet(newValue: Int)
}