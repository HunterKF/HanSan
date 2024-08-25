package com.jaegerapps.hansan.screens.settings.domain.mapper

import com.jaegerapps.hansan.common.data.local.room.entity.GrammarEntity
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.getDetailedTenseFromString
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsFormalityModel
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
                detailedTense = getDetailedTenseFromString(tense),
                isSelected = entries.any { it.selected }
            )
        }

    return Pair(formalityModels, tenseModels)
}
