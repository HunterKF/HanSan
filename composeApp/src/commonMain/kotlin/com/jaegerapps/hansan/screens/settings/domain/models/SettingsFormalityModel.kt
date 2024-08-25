package com.jaegerapps.hansan.screens.settings.domain.models

import com.jaegerapps.hansan.common.models.FormalityType

data class SettingsFormalityModel(
    val formalityType: FormalityType,
    val isSelected: Boolean
)
