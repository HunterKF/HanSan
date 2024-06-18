package com.jaegerapps.hansan.screens.settings.domain.use_cases

import com.jaegerapps.hansan.screens.settings.domain.models.SettingsFormalityModel
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsTenseModel

class CheckEnabledUseCase {
    companion object {
        fun checkFormalitiesAtLeastOneEnabled(list: List<SettingsFormalityModel>, size: Int): Boolean {
            return list.count { !it.isSelected } != size - 1
        }

        fun checkTensesAtLeastOneEnabled(list: List<SettingsTenseModel>, size: Int): Boolean {
            return list.count { !it.isSelected } != size - 1
        }
    }
}