package com.jaegerapps.hansan.presentation.learn.presentation

import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseModel

data class LearnUiState(
    val filterFormality: Formality = Formality.FORMAL_HIGH,
    val tenses: List<Tense> = emptyList()
)