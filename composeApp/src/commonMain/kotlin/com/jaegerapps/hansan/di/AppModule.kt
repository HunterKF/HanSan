package com.jaegerapps.hansan.di

import com.jaegerapps.hansan.common.data.HanSanDataBase
import com.jaegerapps.hansan.root.domain.repo.RootRepo
import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceJson
import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceSettings
import com.jaegerapps.hansan.screens.loading.domain.repo.LoadingRepo
import com.jaegerapps.hansan.screens.onboarding.domain.repo.OnboardingRepo
import com.jaegerapps.hansan.screens.practice.data.local.LocalWordRoomDataSource
import com.jaegerapps.hansan.screens.practice.domain.repo.PracticeRepo
import com.jaegerapps.hansan.screens.settings.data.local.SettingsLocalDataSource
import com.jaegerapps.hansan.screens.settings.domain.repo.SettingsRepo

expect class AppModule {
    val loadingLocalDataSourceJson: LoadingLocalDataSourceJson
    val loadingLocalDataSourceSetting: LoadingLocalDataSourceSettings
    val settingsLocalDataSource: SettingsLocalDataSource
    val dataBase: HanSanDataBase
    val localWordRoomDataSource: LocalWordRoomDataSource

    val loadingRepo: LoadingRepo
    val practiceRepo: PracticeRepo
    val settingsRepo: SettingsRepo
    val onboardingRepo: OnboardingRepo
    val rootRepo: RootRepo
}