package com.jaegerapps.hansan.screens.onboarding.data.local.json

import com.jaegerapps.hansan.common.mappers.parseJsonWord
import com.jaegerapps.hansan.common.models.VerbDto
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.e
import hansan.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

class LocalJsonDataSourceImpl: LocalJsonDataSource  {
    @OptIn(ExperimentalResourceApi::class)
    override suspend fun getWords(): List<VerbDto>? {
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