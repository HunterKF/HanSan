package com.jaegerapps.hansan.screens.loading.data.local

import com.jaegerapps.hansan.common.models.TenseEntity
import com.jaegerapps.hansan.common.models.VerbEntity

interface LoadingLocalDataSourceJson {
    suspend fun getWords(): List<VerbEntity>?
    suspend fun getTenses(): List<TenseEntity>?
}