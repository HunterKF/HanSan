package com.jaegerapps.hansan.screens.settings.domain.mapper

import com.jaegerapps.hansan.common.data.local.room.entity.GrammarEntity
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.getTenseFromString
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsGrammarModel

fun GrammarEntity.toSettingsGrammarModel(): SettingsGrammarModel {
    return SettingsGrammarModel(
        formality = getFormalityFromString(formality = formality),
        tense = getTenseFromString(tense)
    )
}