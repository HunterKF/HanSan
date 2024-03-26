package com.jaegarapps.hansan.practice.domain

import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.ModifierType
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.WordModel
import com.jaegerapps.hansan.common.models.WordTenseModel
import com.jaegerapps.hansan.screens.practice.domain.models.AnswerResponse
import com.jaegerapps.hansan.screens.practice.domain.usecases.EnterAnswer
import kotlin.test.Test
import kotlin.test.assertEquals

class EnterAnswerTest {

    @Test
    fun `Test FORMAL_HIGH_PRESENT_DECLARATIVE expect answer CORRECT`() {
        val wordModel = WordModel(
            dictionaryWord = "하다",
            definition = "to do",
            type = ModifierType.VERBS,
            fhPresentDeclarative = WordTenseModel("합니다", Tense.PRESENT_DECLARATIVE, Formality.FORMAL_HIGH),
            fhPastDeclarative = WordTenseModel("했습니다", Tense.PAST_DECLARATIVE, Formality.FORMAL_HIGH),
            fhFutureDeclarative = WordTenseModel("할 겁니다", Tense.FUTURE_DECLARATIVE, Formality.FORMAL_HIGH),
            flPresentDeclarative = WordTenseModel("해요", Tense.PRESENT_DECLARATIVE, Formality.FORMAL_LOW),
            flPastDeclarative = WordTenseModel("했어요", Tense.PAST_DECLARATIVE, Formality.FORMAL_LOW),
            flFutureDeclarative = WordTenseModel("할 거에요", Tense.FUTURE_DECLARATIVE, Formality.FORMAL_LOW),
            ilPresentDeclarative = WordTenseModel("해", Tense.PRESENT_DECLARATIVE, Formality.INFORMAL_LOW),
            ilPastDeclarative = WordTenseModel("했어", Tense.PAST_DECLARATIVE, Formality.INFORMAL_LOW),
            ilFutureDeclarative = WordTenseModel("할 거야", Tense.FUTURE_DECLARATIVE, Formality.INFORMAL_LOW),

            )
        val target = Tense.PRESENT_DECLARATIVE
        val input = "합니다"

        val result =  EnterAnswer.textAnswer(input, targetTense = target, wordModel)
        assertEquals(AnswerResponse.CORRECT, result)
    }
    @Test
    fun `Test FORMAL_HIGH_PRESENT_DECLARATIVE expect answer WRONG`() {
        val wordModel = WordModel(
            dictionaryWord = "하다",
            definition = "to do",
            type = ModifierType.VERBS,
            fhPresentDeclarative = WordTenseModel("합니다", Tense.PRESENT_DECLARATIVE, Formality.FORMAL_HIGH),
            fhPastDeclarative = WordTenseModel("했습니다", Tense.PAST_DECLARATIVE, Formality.FORMAL_HIGH),
            fhFutureDeclarative = WordTenseModel("할 겁니다", Tense.FUTURE_DECLARATIVE, Formality.FORMAL_HIGH),
            flPresentDeclarative = WordTenseModel("해요", Tense.PRESENT_DECLARATIVE, Formality.FORMAL_LOW),
            flPastDeclarative = WordTenseModel("했어요", Tense.PAST_DECLARATIVE, Formality.FORMAL_LOW),
            flFutureDeclarative = WordTenseModel("할 거에요", Tense.FUTURE_DECLARATIVE, Formality.FORMAL_LOW),
            ilPresentDeclarative = WordTenseModel("해", Tense.PRESENT_DECLARATIVE, Formality.INFORMAL_LOW),
            ilPastDeclarative = WordTenseModel("했어", Tense.PAST_DECLARATIVE, Formality.INFORMAL_LOW),
            ilFutureDeclarative = WordTenseModel("할 거야", Tense.FUTURE_DECLARATIVE, Formality.INFORMAL_LOW),

            )
        val target = Tense.PRESENT_DECLARATIVE
        val input = "합니디"

        val result =  EnterAnswer.textAnswer(input, targetTense = target, wordModel)
        assertEquals(AnswerResponse.WRONG, result)
    }
    @Test
    fun `Test blank FORMAL_HIGH_PRESENT_DECLARATIVE expect answer WRONG`() {
        val wordModel = WordModel(
            dictionaryWord = "하다",
            definition = "to do",
            type = ModifierType.VERBS,
            fhPresentDeclarative = WordTenseModel("합니다", Tense.PRESENT_DECLARATIVE, Formality.FORMAL_HIGH),
            fhPastDeclarative = WordTenseModel("했습니다", Tense.PAST_DECLARATIVE, Formality.FORMAL_HIGH),
            fhFutureDeclarative = WordTenseModel("할 겁니다", Tense.FUTURE_DECLARATIVE, Formality.FORMAL_HIGH),
            flPresentDeclarative = WordTenseModel("해요", Tense.PRESENT_DECLARATIVE, Formality.FORMAL_LOW),
            flPastDeclarative = WordTenseModel("했어요", Tense.PAST_DECLARATIVE, Formality.FORMAL_LOW),
            flFutureDeclarative = WordTenseModel("할 거에요", Tense.FUTURE_DECLARATIVE, Formality.FORMAL_LOW),
            ilPresentDeclarative = WordTenseModel("해", Tense.PRESENT_DECLARATIVE, Formality.INFORMAL_LOW),
            ilPastDeclarative = WordTenseModel("했어", Tense.PAST_DECLARATIVE, Formality.INFORMAL_LOW),
            ilFutureDeclarative = WordTenseModel("할 거야", Tense.FUTURE_DECLARATIVE, Formality.INFORMAL_LOW),

            )
        val target = Tense.PRESENT_DECLARATIVE
        val input = ""

        val result =  EnterAnswer.textAnswer(input, targetTense = target, wordModel)
        assertEquals(AnswerResponse.WRONG, result)
    }
    @Test
    fun `Test wrong answer INFORMAL_LOW_PRESENT_DECLARATIVE expect answer WRONG`() {
        val wordModel = WordModel(
            dictionaryWord = "하다",
            definition = "to do",
            type = ModifierType.VERBS,
            fhPresentDeclarative = WordTenseModel("합니다", Tense.PRESENT_DECLARATIVE, Formality.FORMAL_HIGH),
            fhPastDeclarative = WordTenseModel("했습니다", Tense.PAST_DECLARATIVE, Formality.FORMAL_HIGH),
            fhFutureDeclarative = WordTenseModel("할 겁니다", Tense.FUTURE_DECLARATIVE, Formality.FORMAL_HIGH),
            flPresentDeclarative = WordTenseModel("해요", Tense.PRESENT_DECLARATIVE, Formality.FORMAL_LOW),
            flPastDeclarative = WordTenseModel("했어요", Tense.PAST_DECLARATIVE, Formality.FORMAL_LOW),
            flFutureDeclarative = WordTenseModel("할 거에요", Tense.FUTURE_DECLARATIVE, Formality.FORMAL_LOW),
            ilPresentDeclarative = WordTenseModel("해", Tense.PRESENT_DECLARATIVE, Formality.INFORMAL_LOW),
            ilPastDeclarative = WordTenseModel("했어", Tense.PAST_DECLARATIVE, Formality.INFORMAL_LOW),
            ilFutureDeclarative = WordTenseModel("할 거야", Tense.FUTURE_DECLARATIVE, Formality.INFORMAL_LOW),

            )
        val target = Tense.PRESENT_DECLARATIVE
        val input = "해요"

        val result =  EnterAnswer.textAnswer(input, targetTense = target, wordModel)
        assertEquals(AnswerResponse.WRONG, result)
    }
    @Test
    fun `Test wrong answer FORMAL_LOW_PRESENT_DECLARATIVE expect answer WRONG`() {
        val wordModel = WordModel(
            dictionaryWord = "하다",
            definition = "to do",
            type = ModifierType.VERBS,
            fhPresentDeclarative = WordTenseModel("합니다", Tense.PRESENT_DECLARATIVE, Formality.FORMAL_HIGH),
            fhPastDeclarative = WordTenseModel("했습니다", Tense.PAST_DECLARATIVE, Formality.FORMAL_HIGH),
            fhFutureDeclarative = WordTenseModel("할 겁니다", Tense.FUTURE_DECLARATIVE, Formality.FORMAL_HIGH),
            flPresentDeclarative = WordTenseModel("해요", Tense.PRESENT_DECLARATIVE, Formality.FORMAL_LOW),
            flPastDeclarative = WordTenseModel("했어요", Tense.PAST_DECLARATIVE, Formality.FORMAL_LOW),
            flFutureDeclarative = WordTenseModel("할 거에요", Tense.FUTURE_DECLARATIVE, Formality.FORMAL_LOW),
            ilPresentDeclarative = WordTenseModel("해", Tense.PRESENT_DECLARATIVE, Formality.INFORMAL_LOW),
            ilPastDeclarative = WordTenseModel("했어", Tense.PAST_DECLARATIVE, Formality.INFORMAL_LOW),
            ilFutureDeclarative = WordTenseModel("할 거야", Tense.FUTURE_DECLARATIVE, Formality.INFORMAL_LOW),

            )
        val target = Tense.PRESENT_DECLARATIVE
        val input = "해"

        val result =  EnterAnswer.textAnswer(input, targetTense = target, wordModel)
        assertEquals(AnswerResponse.WRONG, result)
    }
}