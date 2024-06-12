package com.jaegarapps.hansan.practice.domain

import com.jaegerapps.hansan.common.models.DefinitionTranslations
import com.jaegerapps.hansan.common.models.Formalities
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.ModifierType
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.VerbModel
import com.jaegerapps.hansan.common.models.Word
import com.jaegerapps.hansan.common.models.WordTenseModel
import com.jaegerapps.hansan.screens.practice.domain.usecases.WordAndTenseHandler
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class WordAndTenseHandlerTest {
    private val verbList = listOf(
        VerbModel(
            baseWord = "먹다",
            definitionTranslations = DefinitionTranslations(english = "to eat"),
            formalities = Formalities(
                formalHigh = Formality(
                    type = FormalityType.FORMAL_HIGH,
                    conjugation = listOf(
                        Word(tense = Tense.PRESENT_DECLARATIVE, conjugatedWord = "먹습니다", irregular = false, dateExpire = 0),
                        Word(tense = Tense.PAST_DECLARATIVE, conjugatedWord = "먹었습니다", irregular = false, dateExpire = 0),
                        Word(tense = Tense.FUTURE_DECLARATIVE, conjugatedWord = "먹을 것입니다", irregular = false, dateExpire = 0)
                    )
                ),
                formalLow = Formality(
                    type = FormalityType.FORMAL_LOW,
                    conjugation = listOf(
                        Word(tense = Tense.PRESENT_DECLARATIVE, conjugatedWord = "먹어요", irregular = false, dateExpire = 0),
                        Word(tense = Tense.PAST_DECLARATIVE, conjugatedWord = "먹었어요", irregular = false, dateExpire = 0),
                        Word(tense = Tense.FUTURE_DECLARATIVE, conjugatedWord = "먹을 거예요", irregular = false, dateExpire = 0)
                    )
                ),
                informalLow = Formality(
                    type = FormalityType.INFORMAL_LOW,
                    conjugation = listOf(
                        Word(tense = Tense.PRESENT_DECLARATIVE, conjugatedWord = "먹어", irregular = false, dateExpire = 0),
                        Word(tense = Tense.PAST_DECLARATIVE, conjugatedWord = "먹었어", irregular = false, dateExpire = 0),
                        Word(tense = Tense.FUTURE_DECLARATIVE, conjugatedWord = "먹을 거야", irregular = false, dateExpire = 0)
                    )
                )
            )
        ),
        VerbModel(
            baseWord = "말하다",
            definitionTranslations = DefinitionTranslations(english = "to speak"),
            formalities = Formalities(
                formalHigh = Formality(
                    type = FormalityType.FORMAL_HIGH,
                    conjugation = listOf(
                        Word(tense = Tense.PRESENT_DECLARATIVE, conjugatedWord = "말합니다", irregular = false, dateExpire = 0),
                        Word(tense = Tense.PAST_DECLARATIVE, conjugatedWord = "말했습니다", irregular = false, dateExpire = 0),
                        Word(tense = Tense.FUTURE_DECLARATIVE, conjugatedWord = "말할 것입니다", irregular = false, dateExpire = 0)
                    )
                ),
                formalLow = Formality(
                    type = FormalityType.FORMAL_LOW,
                    conjugation = listOf(
                        Word(tense = Tense.PRESENT_DECLARATIVE, conjugatedWord = "말해요", irregular = false, dateExpire = 0),
                        Word(tense = Tense.PAST_DECLARATIVE, conjugatedWord = "말했어요", irregular = false, dateExpire = 0),
                        Word(tense = Tense.FUTURE_DECLARATIVE, conjugatedWord = "말할 거예요", irregular = false, dateExpire = 0)
                    )
                ),
                informalLow = Formality(
                    type = FormalityType.INFORMAL_LOW,
                    conjugation = listOf(
                        Word(tense = Tense.PRESENT_DECLARATIVE, conjugatedWord = "말해", irregular = false, dateExpire = 0),
                        Word(tense = Tense.PAST_DECLARATIVE, conjugatedWord = "말했어", irregular = false, dateExpire = 0),
                        Word(tense = Tense.FUTURE_DECLARATIVE, conjugatedWord = "말할 거야", irregular = false, dateExpire = 0)
                    )
                )
            )
        )
    )

    private val wordModel = VerbModel(
        baseWord = "말하다",
        definitionTranslations = DefinitionTranslations(english = "to speak"),
        formalities = Formalities(
            formalHigh = Formality(
                type = FormalityType.FORMAL_HIGH,
                conjugation = listOf(
                    Word(tense = Tense.PRESENT_DECLARATIVE, conjugatedWord = "말합니다", irregular = false, dateExpire = 0),
                    Word(tense = Tense.PAST_DECLARATIVE, conjugatedWord = "말했습니다", irregular = false, dateExpire = 0),
                    Word(tense = Tense.FUTURE_DECLARATIVE, conjugatedWord = "말할 것입니다", irregular = false, dateExpire = 0)
                )
            ),
            formalLow = Formality(
                type = FormalityType.FORMAL_LOW,
                conjugation = listOf(
                    Word(tense = Tense.PRESENT_DECLARATIVE, conjugatedWord = "말해요", irregular = false, dateExpire = 0),
                    Word(tense = Tense.PAST_DECLARATIVE, conjugatedWord = "말했어요", irregular = false, dateExpire = 0),
                    Word(tense = Tense.FUTURE_DECLARATIVE, conjugatedWord = "말할 거예요", irregular = false, dateExpire = 0)
                )
            ),
            informalLow = Formality(
                type = FormalityType.INFORMAL_LOW,
                conjugation = listOf(
                    Word(tense = Tense.PRESENT_DECLARATIVE, conjugatedWord = "말해", irregular = false, dateExpire = 0),
                    Word(tense = Tense.PAST_DECLARATIVE, conjugatedWord = "말했어", irregular = false, dateExpire = 0),
                    Word(tense = Tense.FUTURE_DECLARATIVE, conjugatedWord = "말할 거야", irregular = false, dateExpire = 0)
                )
            )
        )
    )

    @Test
    fun `get answer options - FORMAL_HIGH - expect list of 4`() {


        val result = WordAndTenseHandler.selectVerb(verbList)

        assertEquals(true, result != null)
    }
    @Test
    fun `get new word - list of PRESENT_DECLARATIVE - Expect 말해`() {

        val targetTense = listOf(Tense.PRESENT_DECLARATIVE)
        val targetFormalityType = FormalityType.INFORMAL_LOW

        val result = WordAndTenseHandler.newWord(
            verb = wordModel,
            targetFormality = targetFormalityType,
            targetTenses = targetTense
        )

        assertEquals(Tense.PRESENT_DECLARATIVE, result.tense)
        assertEquals("말해", result.conjugatedWord)
        assertEquals(false, result.irregular)
    }
    @Test
    fun `get new word - list of PRESENT_DECLARATIVE - Expect PRESENT_DECLARATIVE or PAST_DECLARATIVE`() {

        val targetTense = listOf(Tense.PRESENT_DECLARATIVE, Tense.PAST_DECLARATIVE)
        val targetFormalityType = FormalityType.INFORMAL_LOW

        val result = WordAndTenseHandler.newWord(
            verb = wordModel,
            targetFormality = targetFormalityType,
            targetTenses = targetTense
        )

        assertTrue(targetTense.contains(result.tense))
        assertTrue(result.conjugatedWord == "말해" || result.conjugatedWord == "말했어")
        assertEquals(false, result.irregular)
    }
}