package com.jaegerapps.hansan.common.util

import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.screens.practice.domain.models.PracticeTranslation

fun getTranslation(translationList: List<PracticeTranslation>): String {
    return translationList.firstOrNull { it.languageCode == myLang }?.translation
        ?: translationList.first { it.languageCode == "en" }.translation
}