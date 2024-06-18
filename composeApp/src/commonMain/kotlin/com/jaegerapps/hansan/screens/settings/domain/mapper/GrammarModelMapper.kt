package com.jaegerapps.hansan.screens.settings.domain.mapper

import com.jaegerapps.hansan.common.data.local.room.entity.GrammarEntity
import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.getTenseFromString
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsFormalityModel
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsGrammarModel
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsTenseModel

fun convertGrammarEntities(entities: List<GrammarEntity>): Pair<List<SettingsFormalityModel>, List<SettingsTenseModel>> {
    // Group by formality and convert to SettingsFormalityModel
    val formalityModels = entities
        .groupBy { it.formality }
        .map { (formality, entries) ->
            SettingsFormalityModel(
                formalityType = getFormalityFromString(formality),
                isSelected = entries.any { it.selected }
            )
        }

    // Group by tense and convert to SettingsTenseModel
    val tenseModels = entities
        .groupBy { it.tense }
        .map { (tense, entries) ->
            SettingsTenseModel(
                tense = getTenseFromString(tense),
                isSelected = entries.any { it.selected }
            )
        }

    return Pair(formalityModels, tenseModels)
}
