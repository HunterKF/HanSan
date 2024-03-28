package com.jaegerapps.hansan.di

import android.content.SharedPreferences
import com.russhwolf.settings.SharedPreferencesSettings

import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceJson
import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceJsonImpl
import com.jaegerapps.hansan.screens.loading.data.repo.LoadingRepoImpl
import com.jaegerapps.hansan.screens.loading.domain.repo.LoadingRepo
import com.jaegerapps.hansan.screens.practice.data.repo.PracticeRepoImpl
import com.jaegerapps.hansan.screens.practice.domain.repo.PracticeRepo
import com.jaegerapps.hansan.screens.settings.data.local.SettingsLocalDataSource
import com.jaegerapps.hansan.screens.settings.data.local.SettingsLocalDataSourceImpl
import com.jaegerapps.hansan.screens.settings.data.repo.SettingsRepoImpl
import com.jaegerapps.hansan.screens.settings.domain.repo.SettingsRepo

actual class AppModule(
    private val sharedPreferences: SharedPreferences,
) {

    private val settings = SharedPreferencesSettings(sharedPreferences)

    actual val loadingLocalDataSourceJson: LoadingLocalDataSourceJson by lazy {
        LoadingLocalDataSourceJsonImpl()
    }

    actual val practiceRepo: PracticeRepo by lazy {
        PracticeRepoImpl(
            settings
        )
    }

    actual val loadingRepo: LoadingRepo by lazy {
        LoadingRepoImpl(
            loadingLocalDataSourceJson
        )
    }
    actual val settingsLocalDataSource: SettingsLocalDataSource by lazy {
        SettingsLocalDataSourceImpl(
            settings
        )
    }
    actual val settingsRepo: SettingsRepo by lazy {
        SettingsRepoImpl(
            settingsLocalDataSource
        )
    }
}