package com.jaegerapps.hansan.screens.words.word_individual

import com.jaegerapps.hansan.common.models.VerbModel
import com.jaegerapps.hansan.common.models.WordTenseModel

data class IndividualWordUiState(
    val currentWord: VerbModel,
    val present: List<WordTenseModel> = emptyList(),
    val past: List<WordTenseModel> = emptyList(),
    val future: List<WordTenseModel> = emptyList(),
)
