package com.jaegerapps.hansan

import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.WordModel

data class RootState(
    val tenses: List<TenseModel> = emptyList(),
    val words: List<WordModel> = emptyList(),
    val loading: Boolean = true
)
