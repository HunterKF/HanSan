package com.jaegerapps.hansan.screens.practice.domain.usecases

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
    }
}