package com.jaegerapps.hansan.screens.words.word_individual

import com.jaegerapps.hansan.common.models.WordModel
import com.jaegerapps.hansan.common.models.WordTenseModel

data class IndividualWordUiState(
    val currentWord: WordModel,
    val present: List<WordTenseModel> = emptyList(),
    val past: List<WordTenseModel> = emptyList(),
    val future: List<WordTenseModel> = emptyList(),
)
