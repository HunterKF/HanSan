package com.jaegerapps.hansan.presentation.loading.data.repo

import com.jaegerapps.hansan.common.mappers.toTenseModel
import com.jaegerapps.hansan.common.mappers.toWordModel
import com.jaegerapps.hansan.common.models.WordModel
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.e
import com.jaegerapps.hansan.presentation.loading.data.local.LoadingLocalDataSourceJson
import com.jaegerapps.hansan.presentation.loading.domain.models.LoadingResult
import com.jaegerapps.hansan.presentation.loading.domain.repo.LoadingRepo

class LoadingRepoImpl(
    private val local: LoadingLocalDataSourceJson,
) : LoadingRepo {
    override suspend fun getDefaultData(): Result<LoadingResult> {

        return try {
            val tenses = local.getTenses()
            val words = local.getWords()
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
}