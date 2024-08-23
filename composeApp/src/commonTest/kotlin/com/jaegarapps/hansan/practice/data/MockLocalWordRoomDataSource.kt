package com.jaegarapps.hansan.practice.data

import androidx.compose.runtime.mutableStateOf
import com.jaegerapps.hansan.common.data.local.room.entity.GrammarEntity
import com.jaegerapps.hansan.common.data.local.room.entity.TranslationEntity
import com.jaegerapps.hansan.common.data.local.room.entity.WordEntity
import com.jaegerapps.hansan.screens.practice.data.local.LocalWordRoomDataSource

class MockLocalWordRoomDataSource : LocalWordRoomDataSource {

    private val mockGrammarData = listOf(
        GrammarEntity(1, "present_declarative", "formal_high", true),
        GrammarEntity(2, "past_declarative", "formal_high", true),
        GrammarEntity(3, "future_declarative", "formal_high", true),
        GrammarEntity(4, "present_declarative", "formal_low", true),
        GrammarEntity(5, "past_declarative", "formal_low", true),
        GrammarEntity(6, "future_declarative", "formal_low", true),
        GrammarEntity(7, "present_declarative", "informal_low", true),
        GrammarEntity(8, "past_declarative", "informal_low", true),
        GrammarEntity(9, "future_declarative", "informal_low", true)
    )
    private val mockWordData = listOf(
        WordEntity(1, "가다", "갑니다", 2, "present_declarative", "formal_high", false, 1724449669074),
        WordEntity(2, "가다", "갔습니다", 2, "past_declarative", "formal_high", false, 1724449678761),
        WordEntity(3, "가다", "갈 겁니다", 1, "future_declarative", "formal_high", false, 1724428077791),
        WordEntity(4, "가다", "가요", 2, "present_declarative", "formal_low", false, 1724449676708),
        WordEntity(5, "가다", "갔어요", 1, "past_declarative", "formal_low", false, null),
        WordEntity(6, "가다", "갈 거예요", 1, "future_declarative", "formal_low", false, null),
        WordEntity(7, "가다", "가", 1, "present_declarative", "informal_low", false, null),
        WordEntity(8, "가다", "갔어", 1, "past_declarative", "informal_low", false, null),
        WordEntity(9, "가다", "갈 거야", 2, "future_declarative", "informal_low", false, 1724449671229),
        WordEntity(10, "가르치다", "가르칩니다", 1, "present_declarative", "formal_high", false, null),
        WordEntity(11, "가르치다", "가르쳤습니다", 1, "past_declarative", "formal_high", false, 1724428072197),
        WordEntity(12, "가르치다", "가르칠 겁니다", 1, "future_declarative", "formal_high", false, null),
        WordEntity(13, "가르치다", "가르쳐요", 2, "present_declarative", "formal_low", false, 1724449680259),
        WordEntity(14, "가르치다", "가르쳤어요", 1, "past_declarative", "formal_low", false, null),
        WordEntity(15, "가르치다", "가르칠 게예요", 1, "future_declarative", "formal_low", false, null),
        WordEntity(16, "가르치다", "가르쳐", 1, "present_declarative", "informal_low", false, null),
        WordEntity(17, "가르치다", "가르쳤어", 2, "past_declarative", "informal_low", false, 1724449667948),
        WordEntity(18, "가르치다", "가르칠 거야", 1, "future_declarative", "informal_low", false, 1724428070161)
    )
    private val mockTranslationData = listOf(
        TranslationEntity(1, "가다", "en", "to go"),
        TranslationEntity(2, "가다", "es", "ir"),
        TranslationEntity(3, "가다", "fr", "aller"),
        TranslationEntity(4, "가다", "de", "gehen"),
        TranslationEntity(5, "가르치다", "en", "to teach"),
        TranslationEntity(6, "가르치다", "es", "enseñar"),
        TranslationEntity(7, "가르치다", "fr", "enseigner"),
        TranslationEntity(8, "가르치다", "de", "Lehren")
    )

    private val dailyGoal = mutableStateOf(0)
    private val dailyMax = mutableStateOf(50)

    override suspend fun getGrammar(): List<GrammarEntity> {
        return mockGrammarData
    }

    override suspend fun getWordsByTime(
        time: Long,
        tense: List<String>,
        formality: List<String>,
    ): List<WordEntity> {
        return mockWordData
    }

    override suspend fun getWordsById(
        id: Int,
        tense: List<String>,
        formality: List<String>,
    ): List<WordEntity> {
        return mockWordData

    }

    override suspend fun getWords(tense: List<String>, formality: List<String>): List<WordEntity> {
        return mockWordData

    }

    override suspend fun getTranslation(baseWordList: List<String>): List<TranslationEntity> {
        return mockTranslationData
    }

    override suspend fun updateWord(wordEntity: WordEntity) {
        //update
    }

    override suspend fun getDailyValue(): Pair<Int, Int> {
        return Pair(dailyGoal.value, dailyMax.value)
    }

    override suspend fun updateDailyValue(value: Int): Int {
        dailyGoal.value = dailyGoal.value++
        return dailyGoal.value

    }
}