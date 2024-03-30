package com.jaegerapps.hansan.screens.loading.domain.repo

import com.jaegerapps.hansan.screens.loading.domain.models.LoadingResult

interface LoadingRepo {
    suspend fun getDefaultData(): Result<LoadingResult>
    suspend fun updateDailyGoals()
}