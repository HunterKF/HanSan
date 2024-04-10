package com.jaegerapps.hansan.screens.learn.presentation.tense_list

import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.screens.learn.presentation.components.TenseHeader

data class LearnUiState(
    val filterFormality: Formality = Formality.FORMAL_HIGH,
    val tenses: List<TenseModel> = emptyList(),
    val tensesShow: HashMap<TenseHeader, List<TenseModel>> = hashMapOf()
)