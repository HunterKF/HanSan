package com.jaegerapps.hansan.root.domain.repo

interface RootRepo {
    suspend fun getOnboarding(): Boolean
}