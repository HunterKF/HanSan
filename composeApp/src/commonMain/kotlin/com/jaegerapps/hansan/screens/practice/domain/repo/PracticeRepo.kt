package com.jaegerapps.hansan.screens.practice.domain.repo

import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.ModifierType
import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.common.models.VerbModel
import com.jaegerapps.hansan.common.models.Word

interface PracticeRepo {
    suspend fun getUserSettings(): UserSettings
    suspend fun updateDailyGoalMet(newValue: Int)
    suspend fun insertWord(wordModel: Word)
    suspend fun updateWord(wordModel: Word)
    suspend fun getWords(): List<Word>

}