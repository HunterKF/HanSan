package com.jaegerapps.hansan.screens.practice.data.repo

import com.russhwolf.settings.Settings
import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.common.models.Word
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.stringToType
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.common.util.SettingKeys
import com.jaegerapps.hansan.data.HanSanDataBase
import com.jaegerapps.hansan.screens.practice.domain.mappers.toWord
import com.jaegerapps.hansan.screens.practice.domain.mappers.toWordEntity
import com.jaegerapps.hansan.screens.practice.domain.repo.PracticeRepo
import kotlinx.datetime.Clock

class PracticeRepoImpl(
    private val settings: Settings,
    private val database: HanSanDataBase,
) : PracticeRepo {
    private val wordDao = database.wordDao()
    private val translationDao = database.translationDao()
    private val grammarDao = database.grammarDao()
    override suspend fun getUserSettings(): UserSettings {
        val formality = settings.getString(SettingKeys.FORMALITY, "formal_high")
        val type = settings.getString(SettingKeys.TYPE, "verb")
        val keyboardEnabled = settings.getBoolean(SettingKeys.KEYBOARD_ENABLED, false)
        val presentTenseEnabled = settings.getBoolean(SettingKeys.PRESENT_TENSE_ENABLED, true)
        val pastTenseEnabled = settings.getBoolean(SettingKeys.PAST_TENSE_ENABLED, true)
        val futureTenseEnabled = settings.getBoolean(SettingKeys.FUTURE_TENSE_ENABLED, true)
        val enableReminders = settings.getBoolean(SettingKeys.DAILY_REMINDERS_ENABLED, false)
        val dailyTargetMet = settings.getInt(SettingKeys.DAILY_TARGET_MET, 0)
        val dailyTargetMax = settings.getInt(SettingKeys.DAILY_TARGET_MAX, 50)
        return UserSettings(
            targetFormalityType = getFormalityFromString(formality),
            targetType = stringToType(type),
            keyboardEnabled = keyboardEnabled,
            presentTenseEnabled = presentTenseEnabled,
            pastTenseEnabled = pastTenseEnabled,
            futureTenseEnabled = futureTenseEnabled,
            enableReminders = enableReminders,
            dailyTargetMet = dailyTargetMet,
            dailyTargetMax = dailyTargetMax
        )
    }


    override suspend fun updateDailyGoalMet(newValue: Int) {
        settings.putInt(SettingKeys.DAILY_TARGET_MET, newValue)
    }


    override suspend fun updateWord(wordModel: Word) {
        wordDao.updateWord(wordModel.toWordEntity())
    }

    override suspend fun getWords(): List<Word> {
        val selectedGrammar = grammarDao.getSelectedGrammar()
        Knower.d("PracticeRepoImpl", "Checked selected grammar. $selectedGrammar")
        val currentTime = Clock.System.now().epochSeconds
        Knower.d("PracticeRepoImpl", "Got current time. $currentTime")
        val wordEntities = selectedGrammar.flatMap { grammar ->
            wordDao.getWordsByTime(grammar.tense, grammar.formality, currentTime)
        }
        val words = if (wordEntities.isEmpty()) wordDao.getWords().map { it.toWord() } else {
            wordEntities.map { it.toWord() }

        }
        Knower.d("PracticeRepoImpl", "Attempting to return words. $words")

        return words
    }
}