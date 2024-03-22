package com.jaegerapps.hansan.di

import com.jaegerapps.hansan.presentation.loading.data.local.LoadingLocalDataSourceJson
import com.jaegerapps.hansan.presentation.loading.data.local.LoadingLocalDataSourceJsonImpl
import com.jaegerapps.hansan.presentation.loading.data.repo.LoadingRepoImpl
import com.jaegerapps.hansan.presentation.loading.domain.repo.LoadingRepo
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import com.jaegerapps.hansan.presentation.practice.data.repo.PracticeRepoImpl
import com.jaegerapps.hansan.presentation.practice.domain.repo.PracticeRepo
import platform.Foundation.NSUserDefaults

actual class AppModule {
    private val delegate = NSUserDefaults()
    private val settings: Settings = NSUserDefaultsSettings(delegate)

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
}