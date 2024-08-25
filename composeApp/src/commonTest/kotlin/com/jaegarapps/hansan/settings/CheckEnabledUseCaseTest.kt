package com.jaegarapps.hansan.settings

import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.DetailedTense
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsFormalityModel
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsTenseModel
import com.jaegerapps.hansan.screens.settings.domain.use_cases.CheckEnabledUseCase.Companion.checkFormalitiesAtLeastOneEnabled
import com.jaegerapps.hansan.screens.settings.domain.use_cases.CheckEnabledUseCase.Companion.checkTensesAtLeastOneEnabled
import kotlin.test.Test
import kotlin.test.assertEquals

class CheckEnabledUseCaseTest {


    val formalities = listOf(
        SettingsFormalityModel(
            formalityType = FormalityType.FORMAL_HIGH,
            isSelected = true
        ),
        SettingsFormalityModel(
            formalityType = FormalityType.FORMAL_LOW,
            isSelected = true
        ),
        SettingsFormalityModel(
            formalityType = FormalityType.INFORMAL_LOW,
            isSelected = true
        )
    )

    @Test
    fun `3 enabled`() {
        val formalities = listOf(
            SettingsFormalityModel(
                formalityType = FormalityType.FORMAL_HIGH,
                isSelected = true
            ),
            SettingsFormalityModel(
                formalityType = FormalityType.FORMAL_LOW,
                isSelected = true
            ),
            SettingsFormalityModel(
                formalityType = FormalityType.INFORMAL_LOW,
                isSelected = true
            )
        )
        val expected = true
        println(checkFormalitiesAtLeastOneEnabled(formalities, formalities.size))
        assertEquals(expected, checkFormalitiesAtLeastOneEnabled(formalities,formalities.size))
    }
    @Test
    fun `2 enabled`() {
        val formalities = listOf(
            SettingsFormalityModel(
                formalityType = FormalityType.FORMAL_HIGH,
                isSelected = false
            ),
            SettingsFormalityModel(
                formalityType = FormalityType.FORMAL_LOW,
                isSelected = true
            ),
            SettingsFormalityModel(
                formalityType = FormalityType.INFORMAL_LOW,
                isSelected = true
            )
        )
        val expected = true
        println(checkFormalitiesAtLeastOneEnabled(formalities, formalities.size))
        assertEquals(expected, checkFormalitiesAtLeastOneEnabled(formalities,formalities.size))
    }
    @Test
    fun `1 enabled - return false`() {
        val formalities = listOf(
            SettingsFormalityModel(
                formalityType = FormalityType.FORMAL_HIGH,
                isSelected = false
            ),
            SettingsFormalityModel(
                formalityType = FormalityType.FORMAL_LOW,
                isSelected = false
            ),
            SettingsFormalityModel(
                formalityType = FormalityType.INFORMAL_LOW,
                isSelected = true
            )
        )
        val expected = false
        assertEquals(expected, checkFormalitiesAtLeastOneEnabled(formalities,formalities.size))
    }

    @Test
    fun `3 tenses enabled`() {
        val tense = listOf(
            SettingsTenseModel(
                detailedTense = DetailedTense.PRESENT_DECLARATIVE_FORMAL_HIGH,
                isSelected = true
            ),
            SettingsTenseModel(
                detailedTense = DetailedTense.PAST_DECLARATIVE_FORMAL_HIGH,
                isSelected = true
            ),
            SettingsTenseModel(
                detailedTense = DetailedTense.FUTURE_DECLARATIVE_FORMAL_HIGH,
                isSelected = true
            )
        )
        val expected = true
        assertEquals(expected, checkTensesAtLeastOneEnabled(tense,tense.size))
    }
    @Test
    fun `2 tenses enabled`() {
        val tense = listOf(
            SettingsTenseModel(
                detailedTense = DetailedTense.PRESENT_DECLARATIVE_FORMAL_HIGH,
                isSelected = false
            ),
            SettingsTenseModel(
                detailedTense = DetailedTense.PAST_DECLARATIVE_FORMAL_HIGH,
                isSelected = true
            ),
            SettingsTenseModel(
                detailedTense = DetailedTense.FUTURE_DECLARATIVE_FORMAL_HIGH,
                isSelected = true
            )
        )
        val expected = true
        assertEquals(expected, checkTensesAtLeastOneEnabled(tense,tense.size))
    }
    @Test
    fun `1 tenses enabled - return false`() {
        val tense = listOf(
            SettingsTenseModel(
                detailedTense = DetailedTense.PRESENT_DECLARATIVE_FORMAL_HIGH,
                isSelected = false
            ),
            SettingsTenseModel(
                detailedTense = DetailedTense.PAST_DECLARATIVE_FORMAL_HIGH,
                isSelected = false
            ),
            SettingsTenseModel(
                detailedTense = DetailedTense.FUTURE_DECLARATIVE_FORMAL_HIGH,
                isSelected = true
            )
        )
        val expected = false
        assertEquals(expected, checkTensesAtLeastOneEnabled(tense,tense.size))
    }
}