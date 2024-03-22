package com.jaegerapps.hansan.presentation.loading.domain.repo

import com.jaegerapps.hansan.common.models.WordModel
import com.jaegerapps.hansan.presentation.loading.domain.models.LoadingResult

interface LoadingRepo {
    suspend fun getDefaultData(): Result<LoadingResult>
}