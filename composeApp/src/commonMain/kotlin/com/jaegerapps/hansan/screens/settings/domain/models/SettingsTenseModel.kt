package com.jaegerapps.hansan.screens.settings.domain.models

import com.jaegerapps.hansan.common.models.DetailedTense

data class SettingsTenseModel(
    val detailedTense: DetailedTense,
    val isSelected: Boolean
)
