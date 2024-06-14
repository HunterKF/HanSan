package com.jaegerapps.hansan.root.data.repo

import com.jaegerapps.hansan.common.util.SettingKeys.ONBOARDING
import com.jaegerapps.hansan.root.domain.repo.RootRepo
import com.russhwolf.settings.Settings

class RootRepoImpl(
    private val settings: Settings
): RootRepo {
    override suspend fun getOnboarding(): Boolean {
        return settings.getBoolean(ONBOARDING, true)
    }

}