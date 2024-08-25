package com.jaegerapps.hansan.screens.settings.domain.models

data class SettingsGrammarModel(
    val formality: List<SettingsFormalityModel>,
    val tense: List<SettingsTenseModel>
)
