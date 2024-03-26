package com.jaegerapps.hansan.di

import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceJson
import com.jaegerapps.hansan.screens.loading.domain.repo.LoadingRepo
import com.jaegerapps.hansan.screens.practice.domain.repo.PracticeRepo

expect class AppModule {
    val loadingLocalDataSourceJson: LoadingLocalDataSourceJson
    val loadingRepo: LoadingRepo
    val practiceRepo: PracticeRepo
}