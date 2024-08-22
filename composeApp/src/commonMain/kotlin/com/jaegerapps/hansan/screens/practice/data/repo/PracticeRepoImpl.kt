package com.jaegerapps.hansan.screens.practice.data.repo

import com.russhwolf.settings.Settings
import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.common.util.SettingKeys
import com.jaegerapps.hansan.screens.practice.data.local.LocalWordRoomDataSource
import com.jaegerapps.hansan.screens.practice.domain.mappers.toWordEntity
import com.jaegerapps.hansan.screens.practice.domain.models.PracticeWordModel
import com.jaegerapps.hansan.screens.practice.domain.repo.PracticeRepo
import com.jaegerapps.hansan.screens.practice.domain.usecases.GetWordsByIdUseCase
import com.jaegerapps.hansan.screens.practice.domain.usecases.GetWordsByTimeUseCase
import com.jaegerapps.hansan.screens.practice.domain.usecases.GetWordsNoCursor
import com.jaegerapps.hansan.screens.practice.domain.usecases.UpdateWordUseCase

class PracticeRepoImpl(
    private val settings: Settings,
    private val localWordRoomDataSource: LocalWordRoomDataSource,
) : PracticeRepo {

    /*TODO - Move all of this setting data into the local data source. It shouldn't be done in here*/
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


    override suspend fun updateDailyGoalMet(newValue: Int) {
        settings.putInt(SettingKeys.DAILY_TARGET_MET, newValue)
    }


    override suspend fun updateWord(wordModel: PracticeWordModel) {
        UpdateWordUseCase(localWordRoomDataSource).invoke(wordModel.toWordEntity())
    }

    override suspend fun getWordsByTime(time: Long): List<PracticeWordModel> {
        val selectedGrammar = localWordRoomDataSource.getGrammar()
        Knower.d("PracticeRepoImpl", "Checked selected grammar. $selectedGrammar")

        val words = GetWordsByTimeUseCase(localWordRoomDataSource).invoke(
            time = time,
            formality = selectedGrammar.map { it.formality },
            tense = selectedGrammar.map { it.tense }
        )
        Knower.d("PracticeRepoImpl", "Here are the words found by time. $words")

        return words.shuffled()
    }

    override suspend fun getWordsById(id: Int): List<PracticeWordModel> {
        val selectedGrammar = localWordRoomDataSource.getGrammar()
        Knower.d("PracticeRepoImpl", "Checked selected grammar. $selectedGrammar")

        val words = GetWordsByIdUseCase(localWordRoomDataSource).invoke(
            id = id,
            formality = selectedGrammar.map { it.formality },
            tense = selectedGrammar.map { it.tense }
        )

        Knower.d("PracticeRepoImpl", "Here are the words found by id. $words")
        return words.shuffled()
    }

    override suspend fun getWords(): List<PracticeWordModel> {
        val selectedGrammar = localWordRoomDataSource.getGrammar()
        Knower.d("PracticeRepoImpl", "Checked selected grammar. $selectedGrammar")

        val words = GetWordsNoCursor(localWordRoomDataSource).invoke(
            formality = selectedGrammar.map { it.formality },
            tense = selectedGrammar.map { it.tense }
        )
        Knower.d("PracticeRepoImpl", "Here are the words with no cursor. ${words}")

        return words.shuffled()
    }
}