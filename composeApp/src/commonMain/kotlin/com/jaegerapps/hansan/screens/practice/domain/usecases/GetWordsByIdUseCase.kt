package com.jaegerapps.hansan.screens.practice.domain.usecases

import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.screens.practice.data.local.LocalWordRoomDataSource
import com.jaegerapps.hansan.screens.practice.domain.mappers.toPracticeTranslation
import com.jaegerapps.hansan.screens.practice.domain.mappers.toPracticeWordModel
import com.jaegerapps.hansan.screens.practice.domain.models.PracticeWordModel

class GetWordsByIdUseCase(
    private val roomDataSource: LocalWordRoomDataSource,
) {
    suspend operator fun invoke(id: Int, tense: List<String>, formality: List<String>): List<PracticeWordModel> {

        val words = roomDataSource.getWordsById(id, tense, formality)
        val translations =
            roomDataSource.getTranslation(words.map { it.conjugated_word }).groupBy { it.reference_word }
        Knower.d("GetWordsByIdUseCase", "Here are the words found by id. ${words}")
        return words.map { wordEntity -> wordEntity.toPracticeWordModel(translations[wordEntity.conjugated_word]?.map { it.toPracticeTranslation() }) }

    }
}