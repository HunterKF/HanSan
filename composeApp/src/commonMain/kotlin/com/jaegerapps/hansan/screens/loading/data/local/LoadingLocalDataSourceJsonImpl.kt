package com.jaegerapps.hansan.screens.loading.data.local

import com.jaegerapps.hansan.common.mappers.parseJsonWord
import com.jaegerapps.hansan.common.models.TenseEntity
import com.jaegerapps.hansan.common.models.VerbEntity
import com.jaegerapps.hansan.common.models.parseJsonTense
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.e
import hansan.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
class LoadingLocalDataSourceJsonImpl : LoadingLocalDataSourceJson {
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

    override suspend fun getTenses(): List<TenseEntity>? {
        return try {
            val jsonTenses = Res.readBytes("files/tenses.json").decodeToString()
            parseJsonTense(jsonTenses)
        } catch (e: Exception) {
            e.printStackTrace()
            Knower.e("getTenses", "An error occurred getting the state: ${e.message}")
            null
        }
    }
}