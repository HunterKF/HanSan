package com.jaegerapps.hansan.screens.practice.domain.usecases

import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.WordModel
import com.jaegerapps.hansan.screens.practice.domain.models.AnswerResponse

class EnterAnswer {
    companion object {
        fun textAnswer(
            input: String,
            targetTense: Tense,
            wordModel: WordModel,
        ): AnswerResponse {
            val answer = getTenseModel(targetTense, wordModel)
            return if (input == answer) {
                AnswerResponse.CORRECT
            } else {
                AnswerResponse.WRONG
            }
        }

        private fun getTenseModel(targetTense: Tense, wordModel: WordModel): String {
            val result = listOf(
                wordModel.fhPresentDeclarative,
                wordModel.fhPastDeclarative,
                wordModel.fhFutureDeclarative,
                wordModel.flPresentDeclarative,
                wordModel.flPastDeclarative,
                wordModel.flFutureDeclarative,
                wordModel.ilPresentDeclarative,
                wordModel.ilPastDeclarative,
                wordModel.ilFutureDeclarative,
            ).filter { it.tense == targetTense }
            return result.first().string
        }
    }

}