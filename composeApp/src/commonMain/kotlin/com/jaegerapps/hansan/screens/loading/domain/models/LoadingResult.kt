package com.jaegerapps.hansan.screens.loading.domain.models

import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.WordModel

data class LoadingResult(
    val words: List<WordModel>,
    val tenses: List<TenseModel>
)
