package com.jaegerapps.hansan.screens.words.word_list.presentation

import com.jaegerapps.hansan.common.models.ModifierType
import com.jaegerapps.hansan.common.models.VerbModel

data class WordUiState(
    val wordFilter: ModifierType = ModifierType.VERBS,
//    val wordsShown: List<VerbModel> = emptyList(),
    val wordList: List<VerbModel> = emptyList()
)
