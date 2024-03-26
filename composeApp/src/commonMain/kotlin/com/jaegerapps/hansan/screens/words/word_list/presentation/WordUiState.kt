package com.jaegerapps.hansan.screens.words.word_list.presentation

import com.jaegerapps.hansan.common.models.ModifierType
import com.jaegerapps.hansan.common.models.WordModel

data class WordUiState(
    val wordFilter: ModifierType = ModifierType.VERBS,
    val wordsShown: List<WordModel> = emptyList(),
    val wordList: List<WordModel> = emptyList()
)
