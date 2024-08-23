package com.jaegerapps.hansan.root.domain.repo

import com.jaegerapps.hansan.common.models.VerbModel

interface RootRepo {
    suspend fun getOnboarding(): Boolean
    suspend fun toggleOnboarding()
    suspend fun loadWords(): List<VerbModel>
}