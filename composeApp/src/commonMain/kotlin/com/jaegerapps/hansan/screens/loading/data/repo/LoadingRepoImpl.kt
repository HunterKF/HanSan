package com.jaegerapps.hansan.screens.loading.data.repo

import com.jaegerapps.hansan.common.mappers.toTenseModel
import com.jaegerapps.hansan.common.mappers.toWordModel
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.e
import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceJson
import com.jaegerapps.hansan.screens.loading.data.local.LoadingLocalDataSourceSettings
import com.jaegerapps.hansan.screens.loading.domain.models.LoadingResult
import com.jaegerapps.hansan.screens.loading.domain.repo.LoadingRepo

class LoadingRepoImpl(
    private val localJson: LoadingLocalDataSourceJson,
    private val localSettings: LoadingLocalDataSourceSettings
) : LoadingRepo {
    override suspend fun getDefaultData(): Result<LoadingResult> {

        return try {
            val tenses = localJson.getTenses()
            val words = localJson.getWords()
            if (!words.isNullOrEmpty() && !tenses.isNullOrEmpty()) {
                Result.success(
                    LoadingResult(
                        words.map { it.toWordModel() },
                        tenses.map { it.toTenseModel() })
                )
            } else {
                Result.failure(Throwable())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Knower.e("getDefaultData", "An error occurred getting the state: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun updateDailyGoals() {
        try {
            localSettings.updateDailyTasks()
       } catch (e: Exception) {
           e.printStackTrace()
           Knower.e("getDefaultData", "An error occurred getting the state: ${e.message}")
       }
    }
}