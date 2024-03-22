package com.jaegerapps.hansan.common.models

data class UserSettings(
    val targetType: ModifierType,
    val targetFormality: Formality,
    val keyboardEnabled: Boolean
)