package com.jaegerapps.hansan.screens.learn.presentation

import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.TenseModel

data class LearnUiState(
    val filterFormality: Formality = Formality.FORMAL_HIGH,
    val tenses: List<TenseModel> = emptyList(),
    val tensesShow: List<TenseModel> = emptyList()
)