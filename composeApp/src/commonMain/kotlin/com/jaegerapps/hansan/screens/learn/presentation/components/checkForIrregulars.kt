package com.jaegerapps.hansan.screens.learn.presentation.components

import com.jaegerapps.hansan.common.models.TenseModel

fun checkForIrregulars(tenseModel: TenseModel): Boolean {
    return tenseModel.irregularSieut != null ||tenseModel.irregularDieut != null ||tenseModel.irregularBieub != null ||tenseModel.irregularEu != null ||tenseModel.irregularReu != null || tenseModel.irregularRieul != null
}