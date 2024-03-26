package com.jaegerapps.hansan.di

import android.content.SharedPreferences
import com.russhwolf.settings.SharedPreferencesSettings

import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceJson
import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceJsonImpl
import com.jaegerapps.hansan.screens.loading.data.repo.LoadingRepoImpl
import com.jaegerapps.hansan.screens.loading.domain.repo.LoadingRepo
import com.jaegerapps.hansan.screens.practice.data.repo.PracticeRepoImpl
import com.jaegerapps.hansan.screens.practice.domain.repo.PracticeRepo

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
}