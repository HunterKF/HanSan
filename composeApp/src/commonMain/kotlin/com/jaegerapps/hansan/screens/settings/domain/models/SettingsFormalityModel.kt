package com.jaegerapps.hansan.screens.settings.domain.models

import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.Tense

data class SettingsFormalityModel(
    val formalityType: FormalityType,
    val isSelected: Boolean
)
