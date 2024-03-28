package com.jaegerapps.hansan.di

import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceJson
import com.jaegerapps.hansan.screens.loading.domain.repo.LoadingRepo
import com.jaegerapps.hansan.screens.practice.domain.repo.PracticeRepo
import com.jaegerapps.hansan.screens.settings.data.local.SettingsLocalDataSource
import com.jaegerapps.hansan.screens.settings.domain.repo.SettingsRepo

expect class AppModule {
    val loadingLocalDataSourceJson: LoadingLocalDataSourceJson
    val settingsLocalDataSource: SettingsLocalDataSource

    val loadingRepo: LoadingRepo
    val practiceRepo: PracticeRepo
    val settingsRepo: SettingsRepo
}