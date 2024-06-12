package com.jaegerapps.hansan.di

import com.jaegerapps.hansan.data.HanSanDataBase
import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceJson
import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceSettings
import com.jaegerapps.hansan.screens.loading.domain.repo.LoadingRepo
import com.jaegerapps.hansan.screens.practice.domain.repo.PracticeRepo
import com.jaegerapps.hansan.screens.settings.data.local.SettingsLocalDataSource
import com.jaegerapps.hansan.screens.settings.domain.repo.SettingsRepo

expect class AppModule {
    val loadingLocalDataSourceJson: LoadingLocalDataSourceJson
    val loadingLocalDataSourceSetting: LoadingLocalDataSourceSettings
    val settingsLocalDataSource: SettingsLocalDataSource
    val dataBase: HanSanDataBase

    val loadingRepo: LoadingRepo
    val practiceRepo: PracticeRepo
    val settingsRepo: SettingsRepo
}