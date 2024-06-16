package com.jaegerapps.hansan.screens.practice.domain.usecases

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
            roomDataSource.getTranslation(words.map { it.conjugated_word }).groupBy { it.reference_word }
        return words
            .map { word -> word.toPracticeWordModel(translations[word.conjugated_word]?.map { it.toPracticeTranslation() }) }

    }
}