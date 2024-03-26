package com.jaegerapps.hansan.screens.loading.data.local

import com.jaegerapps.hansan.common.models.TenseEntity
import com.jaegerapps.hansan.common.models.WordEntity

interface LoadingLocalDataSourceJson {
    suspend fun getWords(): List<WordEntity>?
    suspend fun getTenses(): List<TenseEntity>?
}