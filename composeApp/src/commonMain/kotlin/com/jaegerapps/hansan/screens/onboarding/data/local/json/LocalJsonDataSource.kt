package com.jaegerapps.hansan.screens.onboarding.data.local.json

import com.jaegerapps.hansan.common.models.VerbDto

interface LocalJsonDataSource {
    suspend fun getWords(): List<VerbDto>?
}