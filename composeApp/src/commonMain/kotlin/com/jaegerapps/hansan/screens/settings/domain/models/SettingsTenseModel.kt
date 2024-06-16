package com.jaegerapps.hansan.screens.settings.domain.models

import com.jaegerapps.hansan.common.models.Tense

data class SettingsTenseModel(
    val tense: Tense,
    val isSelected: Boolean
)
