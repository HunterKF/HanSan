package com.jaegerapps.hansan.screens.loading.data.local

import com.jaegerapps.hansan.common.models.TenseEntity
import com.jaegerapps.hansan.common.models.VerbDto

interface LoadingLocalDataSourceJson {
    suspend fun getWords(): List<VerbDto>?
    suspend fun getTenses(): List<TenseEntity>?
}