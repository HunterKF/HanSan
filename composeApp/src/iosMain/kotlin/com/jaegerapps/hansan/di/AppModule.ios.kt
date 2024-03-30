package com.jaegerapps.hansan.di

import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceJson
import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceJsonImpl
import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceSettings
import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceSettingsImpl
import com.jaegerapps.hansan.screens.loading.data.repo.LoadingRepoImpl
import com.jaegerapps.hansan.screens.loading.domain.repo.LoadingRepo
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import com.jaegerapps.hansan.screens.practice.data.repo.PracticeRepoImpl
import com.jaegerapps.hansan.screens.practice.domain.repo.PracticeRepo
import com.jaegerapps.hansan.screens.settings.data.local.SettingsLocalDataSource
import com.jaegerapps.hansan.screens.settings.data.local.SettingsLocalDataSourceImpl
import com.jaegerapps.hansan.screens.settings.data.repo.SettingsRepoImpl
import com.jaegerapps.hansan.screens.settings.domain.repo.SettingsRepo
import platform.Foundation.NSUserDefaults

actual class AppModule {
    private val delegate = NSUserDefaults()
    private val settings: Settings = NSUserDefaultsSettings(delegate)

    actual val loadingLocalDataSourceJson: LoadingLocalDataSourceJson by lazy {
        LoadingLocalDataSourceJsonImpl()
    }
    actual val loadingLocalDataSourceSetting: LoadingLocalDataSourceSettings by lazy {
        LoadingLocalDataSourceSettingsImpl(
            settings
        )
    }


    actual val practiceRepo: PracticeRepo by lazy {
        PracticeRepoImpl(
            settings
        )
    }

    actual val loadingRepo: LoadingRepo by lazy {
        LoadingRepoImpl(
            loadingLocalDataSourceJson,
            loadingLocalDataSourceSetting
        )
    }
    actual val settingsLocalDataSource: SettingsLocalDataSource by lazy {
        SettingsLocalDataSourceImpl(settings)
    }
    actual val settingsRepo: SettingsRepo by lazy {
        SettingsRepoImpl(settingsLocalDataSource)
    }
}