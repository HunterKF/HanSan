package com.jaegerapps.hansan.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jaegerapps.hansan.data.HanSanDataBase
import com.russhwolf.settings.SharedPreferencesSettings

import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceJson
import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceJsonImpl
import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceSettings
import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceSettingsImpl
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
    private val context: Context

) {

    private val settings = SharedPreferencesSettings(sharedPreferences)

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
            settings,
            dataBase
        )
    }

    actual val loadingRepo: LoadingRepo by lazy {
        LoadingRepoImpl(
            loadingLocalDataSourceJson,
            loadingLocalDataSourceSetting
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
    actual val dataBase: HanSanDataBase by lazy {
        getDatabaseBuilder(context).build()
    }

    private fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<HanSanDataBase> {
        val appContext = ctx.applicationContext
        val dbFile = appContext.getDatabasePath("my_room.db")
        return Room.databaseBuilder<HanSanDataBase>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}