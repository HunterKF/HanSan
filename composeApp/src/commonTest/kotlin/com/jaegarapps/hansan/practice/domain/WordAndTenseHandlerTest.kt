package com.jaegarapps.hansan.practice.domain

import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.ModifierType
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.WordModel
import com.jaegerapps.hansan.common.models.WordTenseModel
import com.jaegerapps.hansan.screens.practice.domain.usecases.WordAndTenseHandler
import kotlin.test.Test
import kotlin.test.assertEquals

class WordAndTenseHandlerTest {

    @Test
    fun `get answer options - FORMAL_HIGH - expect list of 4`() {
        val wordModel = WordModel(
            dictionaryWord = "하다",
            definition = "to do",
            type = ModifierType.VERBS,
            irregular = false,
            fhPresentDeclarative = WordTenseModel(
                "합니다",
                Tense.PRESENT_DECLARATIVE,
                Formality.FORMAL_HIGH
            ),
            fhPastDeclarative = WordTenseModel(
                "했습니다",
                Tense.PAST_DECLARATIVE,
                Formality.FORMAL_HIGH
            ),
            fhFutureDeclarative = WordTenseModel(
                "할 겁니다",
                Tense.FUTURE_DECLARATIVE,
                Formality.FORMAL_HIGH
            ),
            flPresentDeclarative = WordTenseModel(
                "해요",
                Tense.PRESENT_DECLARATIVE,
                Formality.FORMAL_LOW
            ),
            flPastDeclarative = WordTenseModel("했어요", Tense.PAST_DECLARATIVE, Formality.FORMAL_LOW),
            flFutureDeclarative = WordTenseModel(
                "할 거에요",
                Tense.FUTURE_DECLARATIVE,
                Formality.FORMAL_LOW
            ),
            ilPresentDeclarative = WordTenseModel(
                "해",
                Tense.PRESENT_DECLARATIVE,
                Formality.INFORMAL_LOW
            ),
            ilPastDeclarative = WordTenseModel(
                "했어",
                Tense.PAST_DECLARATIVE,
                Formality.INFORMAL_LOW
            ),
            ilFutureDeclarative = WordTenseModel(
                "할 거야",
                Tense.FUTURE_DECLARATIVE,
                Formality.INFORMAL_LOW
            ),

            )
        val targetTense = Tense.PAST_DECLARATIVE
        val targetFormality = Formality.FORMAL_HIGH

        val result = WordAndTenseHandler.newAnswerOptions(wordModel, tense = targetTense, targetFormality)

        assertEquals(true, result.contains("했습니다"))
        assertEquals(4, result.size)

    }
    @Test
    fun `get answer options - INFORMAL_LOW - expect list of 4`() {
        val wordModel = WordModel(
            dictionaryWord = "하다",
            definition = "to do",
            type = ModifierType.VERBS,
            irregular = false,
            fhPresentDeclarative = WordTenseModel(
                "합니다",
                Tense.PRESENT_DECLARATIVE,
                Formality.FORMAL_HIGH
            ),
            fhPastDeclarative = WordTenseModel(
                "했습니다",
                Tense.PAST_DECLARATIVE,
                Formality.FORMAL_HIGH
            ),
            fhFutureDeclarative = WordTenseModel(
                "할 겁니다",
                Tense.FUTURE_DECLARATIVE,
                Formality.FORMAL_HIGH
            ),
            flPresentDeclarative = WordTenseModel(
                "해요",
                Tense.PRESENT_DECLARATIVE,
                Formality.FORMAL_LOW
            ),
            flPastDeclarative = WordTenseModel("했어요", Tense.PAST_DECLARATIVE, Formality.FORMAL_LOW),
            flFutureDeclarative = WordTenseModel(
                "할 거에요",
                Tense.FUTURE_DECLARATIVE,
                Formality.FORMAL_LOW
            ),
            ilPresentDeclarative = WordTenseModel(
                "해",
                Tense.PRESENT_DECLARATIVE,
                Formality.INFORMAL_LOW
            ),
            ilPastDeclarative = WordTenseModel(
                "했어",
                Tense.PAST_DECLARATIVE,
                Formality.INFORMAL_LOW
            ),
            ilFutureDeclarative = WordTenseModel(
                "할 거야",
                Tense.FUTURE_DECLARATIVE,
                Formality.INFORMAL_LOW
            ),

            )
        val targetTense = Tense.PAST_DECLARATIVE
        val targetFormality = Formality.INFORMAL_LOW

        val result = WordAndTenseHandler.newAnswerOptions(wordModel, tense = targetTense, targetFormality)

        assertEquals(true, result.contains("했어"))
        assertEquals(4, result.size)

    }
}