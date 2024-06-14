package com.jaegerapps.hansan.screens.onboarding.data.local.json

import com.jaegerapps.hansan.common.models.VerbEntity

interface LocalJsonDataSource {
    suspend fun getWords(): List<VerbEntity>?
}