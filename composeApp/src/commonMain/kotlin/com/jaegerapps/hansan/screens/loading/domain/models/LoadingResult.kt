package com.jaegerapps.hansan.screens.loading.domain.models

import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.VerbModel

data class LoadingResult(
    val words: List<VerbModel>,
    val tenses: List<TenseModel>
)
