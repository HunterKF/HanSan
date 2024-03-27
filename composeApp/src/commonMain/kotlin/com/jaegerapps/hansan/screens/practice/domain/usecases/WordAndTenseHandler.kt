package com.jaegerapps.hansan.screens.practice.domain.usecases

import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.WordModel

class WordAndTenseHandler {
    companion object {
        fun newWord(list: List<WordModel>): WordModel {
            return list.random()
        }

        fun newTense(list: List<TenseModel>): TenseModel {
            return list.random()
        }

        fun newAnswerOptions(wordModel: WordModel, tense: Tense, formality: Formality): List<String> {
            val correctAnswer = getCorrectAnswer(tense, wordModel, formality = formality)
            val wrongAnswers = getWrongAnswers( wordModel, correctAnswer)
            return wrongAnswers.plus(correctAnswer).shuffled()
        }

        private fun getCorrectAnswer(
            targetTense: Tense,
            wordModel: WordModel,
            formality: Formality,
        ): String {
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
            ).filter { it.tense == targetTense && it.formality == formality }
            return result.first().string
        }

        private fun getWrongAnswers(
            wordModel: WordModel,
            correctAnswer: String,
        ): List<String> {
            var result = listOf(
                wordModel.fhPresentDeclarative,
                wordModel.fhPastDeclarative,
                wordModel.fhFutureDeclarative,
                wordModel.flPresentDeclarative,
                wordModel.flPastDeclarative,
                wordModel.flFutureDeclarative,
                wordModel.ilPresentDeclarative,
                wordModel.ilPastDeclarative,
                wordModel.ilFutureDeclarative,
            )
            var index = 0
            var list = emptyList<String>()
            while (index < 3) {
                val randomString = result.random()
                if (randomString.string != correctAnswer) {
                    list = list.plus(randomString.string)
                    index++
                    result = result.minus(randomString)
                }
            }
            return list
        }
    }
}