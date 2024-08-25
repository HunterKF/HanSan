package com.jaegerapps.hansan.screens.words.word_individual

import com.jaegerapps.hansan.common.models.VerbModel
import com.jaegerapps.hansan.common.models.Word
import com.jaegerapps.hansan.common.models.WordTenseModel

data class IndividualWordUiState(
    val currentWord: VerbModel,
    val present: List<Word>,
    val past:  List<Word>,
    val future: List<Word>,
    val other: List<Word>
)
