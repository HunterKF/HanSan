package com.jaegerapps.hansan.screens.practice.domain.usecases

import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.screens.practice.data.local.LocalWordRoomDataSource
import com.jaegerapps.hansan.screens.practice.domain.mappers.toPracticeTranslation
import com.jaegerapps.hansan.screens.practice.domain.mappers.toPracticeWordModel
import com.jaegerapps.hansan.screens.practice.domain.models.PracticeWordModel

class GetWordsByTimeUseCase(
    private val roomDataSource: LocalWordRoomDataSource,
) {
    suspend operator fun invoke(time: Long, tense: List<String>, formality: List<String>): List<PracticeWordModel> {

        val words = roomDataSource.getWordsByTime(time, tense, formality)
        val translations =
            roomDataSource.getTranslation(words.map { it.base_word }).groupBy { it.reference_word }
        Knower.d("GetWordsByTimeUseCase", "Here are the translations: $translations")

        val mappedWithTranslations = words
            .map { word -> word.toPracticeWordModel(translations[word.base_word]?.map { it.toPracticeTranslation() }) }
        Knower.d("GetWordsByTimeUseCase", "Here are the mappedWithTranslations: $translations")

        return mappedWithTranslations

    }
}