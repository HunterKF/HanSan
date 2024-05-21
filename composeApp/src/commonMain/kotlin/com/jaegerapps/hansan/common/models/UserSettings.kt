package com.jaegerapps.hansan.common.models

data class UserSettings(
    val targetType: ModifierType,
    val targetFormalityType: FormalityType,
    val keyboardEnabled: Boolean,

    val presentTenseEnabled: Boolean,
    val pastTenseEnabled: Boolean,
    val futureTenseEnabled: Boolean,
    val enableReminders: Boolean,
    val dailyTargetMax: Int,
    val dailyTargetMet: Int
)