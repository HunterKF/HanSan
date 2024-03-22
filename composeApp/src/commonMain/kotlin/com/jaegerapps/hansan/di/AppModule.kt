package com.jaegerapps.hansan.di

import com.jaegerapps.hansan.presentation.loading.data.local.LoadingLocalDataSourceJson
import com.jaegerapps.hansan.presentation.loading.domain.repo.LoadingRepo
import com.jaegerapps.hansan.presentation.practice.domain.repo.PracticeRepo

expect class AppModule {
    val loadingLocalDataSourceJson: LoadingLocalDataSourceJson
    val loadingRepo: LoadingRepo
    val practiceRepo: PracticeRepo
}