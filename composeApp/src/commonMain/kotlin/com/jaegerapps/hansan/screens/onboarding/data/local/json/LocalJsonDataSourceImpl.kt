package com.jaegerapps.hansan.screens.onboarding.data.local.json

import com.jaegerapps.hansan.common.mappers.parseJsonWord
import com.jaegerapps.hansan.common.mappers.toTenseModel
import com.jaegerapps.hansan.common.mappers.toVerbModel
import com.jaegerapps.hansan.common.models.VerbEntity
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.e
import com.jaegerapps.hansan.screens.loading.domain.models.LoadingResult
import hansan.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

class LocalJsonDataSourceImpl: LocalJsonDataSource  {
    @OptIn(ExperimentalResourceApi::class)
    override suspend fun getWords(): List<VerbEntity>? {
        return try {

            val jsonWords = Res.readBytes("files/new_words.json").decodeToString()
            parseJsonWord(jsonWords)
        } catch (e: Exception) {
            e.printStackTrace()
            Knower.e("getWords", "An error occurred getting the state: ${e.message}")
            null
        }
    }

}