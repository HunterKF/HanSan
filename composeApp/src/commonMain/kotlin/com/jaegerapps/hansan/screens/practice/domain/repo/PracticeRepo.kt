package com.jaegerapps.hansan.screens.practice.domain.repo

import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.ModifierType
import com.jaegerapps.hansan.common.models.UserSettings

interface PracticeRepo {
    suspend fun getUserSettings(): UserSettings
    suspend fun updateUserSettingsType(type: ModifierType)
    suspend fun updateUserSettingsFormality(formality: Formality)
    suspend fun updateUserSettingsKeyboard(enabled: Boolean): Boolean
}