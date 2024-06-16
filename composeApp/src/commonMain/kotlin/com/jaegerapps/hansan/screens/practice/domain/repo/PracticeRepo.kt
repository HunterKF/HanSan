package com.jaegerapps.hansan.screens.practice.domain.repo

import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.common.models.Word
import com.jaegerapps.hansan.screens.practice.domain.models.PracticeWordModel

interface PracticeRepo {
    suspend fun getUserSettings(): UserSettings
    suspend fun updateDailyGoalMet(newValue: Int)
    suspend fun updateWord(wordModel: PracticeWordModel)
    suspend fun getWordsByTime(time: Long): List<PracticeWordModel>
    suspend fun getWordsById(id: Int): List<PracticeWordModel>
    suspend fun getWords(): List<PracticeWordModel>

}