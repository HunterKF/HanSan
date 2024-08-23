package com.jaegerapps.hansan.root.data.repo

import com.jaegerapps.hansan.common.mappers.parseJsonWord
import com.jaegerapps.hansan.common.mappers.toVerbModel
import com.jaegerapps.hansan.common.models.VerbModel
import com.jaegerapps.hansan.common.util.SettingKeys
import com.jaegerapps.hansan.common.util.SettingKeys.ONBOARDING
import com.jaegerapps.hansan.root.domain.repo.RootRepo
import com.russhwolf.settings.Settings
import hansan.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

class RootRepoImpl(
    private val settings: Settings
): RootRepo {
    override suspend fun getOnboarding(): Boolean {
        return settings.getBoolean(ONBOARDING, true)
    }

    override suspend fun toggleOnboarding() {
        settings.putBoolean(ONBOARDING, false)
    }

    @OptIn(ExperimentalResourceApi::class)
    override suspend fun loadWords(): List<VerbModel> {
        //Used in the all word screen
        //Loads words from the JSON to be displayed on the all word screen
        val jsonWords = Res.readBytes("files/new_words.json").decodeToString()
        val verbDto = parseJsonWord(jsonWords)
        val verbs = verbDto.map { it.toVerbModel() }
        return verbs
    }


}