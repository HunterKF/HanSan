package com.jaegerapps.hansan.root

import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.VerbModel

data class RootState(
    val tenses: List<TenseModel> = emptyList(),
    val words: List<VerbModel> = emptyList(),
    val loading: Boolean = true
)
