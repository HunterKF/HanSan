package com.jaegarapps.hansan.practice.domain

import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.WordModel
import com.jaegerapps.hansan.common.models.WordTenseModel
import com.jaegerapps.hansan.presentation.practice.domain.models.AnswerResponse
import com.jaegerapps.hansan.presentation.practice.domain.usecases.EnterAnswer
import kotlin.test.Test
import kotlin.test.assertEquals

class EnterAnswerTest {

    @Test
    fun `Test FORMAL_HIGH_PRESENT_DECLARATIVE expect answer CORRECT`() {
        val wordModel = WordModel(
            dictionaryWord = "하다",
            definition = "to do",
            fhPresentDeclarative = WordTenseModel("합니다", Tense.FORMAL_HIGH_PRESENT_DECLARATIVE) ,
            fhPastDeclarative = WordTenseModel("했습니다",  Tense.FORMAL_HIGH_PAST_DECLARATIVE),
            fhFutureDeclarative =  WordTenseModel("할 겁니다",  Tense.FORMAL_HIGH_FUTURE_DECLARATIVE),
            flPresentDeclarative =  WordTenseModel("해요",  Tense.FORMAL_LOW_PRESENT_DECLARATIVE) ,
            flPastDeclarative =  WordTenseModel("했어요",  Tense.FORMAL_LOW_PAST_DECLARATIVE),
            flFutureDeclarative =  WordTenseModel("할 거에요", Tense.FORMAL_LOW_FUTURE_DECLARATIVE),
            ilPresentDeclarative =  WordTenseModel("해",  Tense.INFORMAL_LOW_PRESENT_DECLARATIVE) ,
            ilPastDeclarative =  WordTenseModel("했어",  Tense.INFORMAL_LOW_PAST_DECLARATIVE),
            ilFutureDeclarative =  WordTenseModel("할 거야",  Tense.INFORMAL_LOW_FUTURE_DECLARATIVE),

            )
        val target = Tense.FORMAL_HIGH_PRESENT_DECLARATIVE
        val input = "합니다"

        val result =  EnterAnswer.textAnswer(input, targetTense = target, wordModel)
        assertEquals(AnswerResponse.CORRECT, result)
    }
    @Test
    fun `Test FORMAL_HIGH_PRESENT_DECLARATIVE expect answer WRONG`() {
        val wordModel = WordModel(
            dictionaryWord = "하다",
            definition = "to do",
            fhPresentDeclarative = WordTenseModel("합니다", Tense.FORMAL_HIGH_PRESENT_DECLARATIVE) ,
            fhPastDeclarative = WordTenseModel("했습니다",  Tense.FORMAL_HIGH_PAST_DECLARATIVE),
            fhFutureDeclarative =  WordTenseModel("할 겁니다",  Tense.FORMAL_HIGH_FUTURE_DECLARATIVE),
            flPresentDeclarative =  WordTenseModel("해요",  Tense.FORMAL_LOW_PRESENT_DECLARATIVE) ,
            flPastDeclarative =  WordTenseModel("했어요",  Tense.FORMAL_LOW_PAST_DECLARATIVE),
            flFutureDeclarative =  WordTenseModel("할 거에요", Tense.FORMAL_LOW_FUTURE_DECLARATIVE),
            ilPresentDeclarative =  WordTenseModel("해",  Tense.INFORMAL_LOW_PRESENT_DECLARATIVE) ,
            ilPastDeclarative =  WordTenseModel("했어",  Tense.INFORMAL_LOW_PAST_DECLARATIVE),
            ilFutureDeclarative =  WordTenseModel("할 거야",  Tense.INFORMAL_LOW_FUTURE_DECLARATIVE),

            )
        val target = Tense.FORMAL_HIGH_PRESENT_DECLARATIVE
        val input = "합니디"

        val result =  EnterAnswer.textAnswer(input, targetTense = target, wordModel)
        assertEquals(AnswerResponse.WRONG, result)
    }
    @Test
    fun `Test blank FORMAL_HIGH_PRESENT_DECLARATIVE expect answer WRONG`() {
        val wordModel = WordModel(
            dictionaryWord = "하다",
            definition = "to do",
            fhPresentDeclarative = WordTenseModel("합니다", Tense.FORMAL_HIGH_PRESENT_DECLARATIVE) ,
            fhPastDeclarative = WordTenseModel("했습니다",  Tense.FORMAL_HIGH_PAST_DECLARATIVE),
            fhFutureDeclarative =  WordTenseModel("할 겁니다",  Tense.FORMAL_HIGH_FUTURE_DECLARATIVE),
            flPresentDeclarative =  WordTenseModel("해요",  Tense.FORMAL_LOW_PRESENT_DECLARATIVE) ,
            flPastDeclarative =  WordTenseModel("했어요",  Tense.FORMAL_LOW_PAST_DECLARATIVE),
            flFutureDeclarative =  WordTenseModel("할 거에요", Tense.FORMAL_LOW_FUTURE_DECLARATIVE),
            ilPresentDeclarative =  WordTenseModel("해",  Tense.INFORMAL_LOW_PRESENT_DECLARATIVE) ,
            ilPastDeclarative =  WordTenseModel("했어",  Tense.INFORMAL_LOW_PAST_DECLARATIVE),
            ilFutureDeclarative =  WordTenseModel("할 거야",  Tense.INFORMAL_LOW_FUTURE_DECLARATIVE),

            )
        val target = Tense.FORMAL_HIGH_PRESENT_DECLARATIVE
        val input = ""

        val result =  EnterAnswer.textAnswer(input, targetTense = target, wordModel)
        assertEquals(AnswerResponse.WRONG, result)
    }
    @Test
    fun `Test wrong answer INFORMAL_LOW_PRESENT_DECLARATIVE expect answer WRONG`() {
        val wordModel = WordModel(
            dictionaryWord = "하다",
            definition = "to do",
            fhPresentDeclarative = WordTenseModel("합니다", Tense.FORMAL_HIGH_PRESENT_DECLARATIVE) ,
            fhPastDeclarative = WordTenseModel("했습니다",  Tense.FORMAL_HIGH_PAST_DECLARATIVE),
            fhFutureDeclarative =  WordTenseModel("할 겁니다",  Tense.FORMAL_HIGH_FUTURE_DECLARATIVE),
            flPresentDeclarative =  WordTenseModel("해요",  Tense.FORMAL_LOW_PRESENT_DECLARATIVE) ,
            flPastDeclarative =  WordTenseModel("했어요",  Tense.FORMAL_LOW_PAST_DECLARATIVE),
            flFutureDeclarative =  WordTenseModel("할 거에요", Tense.FORMAL_LOW_FUTURE_DECLARATIVE),
            ilPresentDeclarative =  WordTenseModel("해",  Tense.INFORMAL_LOW_PRESENT_DECLARATIVE) ,
            ilPastDeclarative =  WordTenseModel("했어",  Tense.INFORMAL_LOW_PAST_DECLARATIVE),
            ilFutureDeclarative =  WordTenseModel("할 거야",  Tense.INFORMAL_LOW_FUTURE_DECLARATIVE),

            )
        val target = Tense.INFORMAL_LOW_PRESENT_DECLARATIVE
        val input = "해요"

        val result =  EnterAnswer.textAnswer(input, targetTense = target, wordModel)
        assertEquals(AnswerResponse.WRONG, result)
    }
    @Test
    fun `Test wrong answer FORMAL_LOW_PRESENT_DECLARATIVE expect answer WRONG`() {
        val wordModel = WordModel(
            dictionaryWord = "하다",
            definition = "to do",
            fhPresentDeclarative = WordTenseModel("합니다", Tense.FORMAL_HIGH_PRESENT_DECLARATIVE) ,
            fhPastDeclarative = WordTenseModel("했습니다",  Tense.FORMAL_HIGH_PAST_DECLARATIVE),
            fhFutureDeclarative =  WordTenseModel("할 겁니다",  Tense.FORMAL_HIGH_FUTURE_DECLARATIVE),
            flPresentDeclarative =  WordTenseModel("해요",  Tense.FORMAL_LOW_PRESENT_DECLARATIVE) ,
            flPastDeclarative =  WordTenseModel("했어요",  Tense.FORMAL_LOW_PAST_DECLARATIVE),
            flFutureDeclarative =  WordTenseModel("할 거에요", Tense.FORMAL_LOW_FUTURE_DECLARATIVE),
            ilPresentDeclarative =  WordTenseModel("해",  Tense.INFORMAL_LOW_PRESENT_DECLARATIVE) ,
            ilPastDeclarative =  WordTenseModel("했어",  Tense.INFORMAL_LOW_PAST_DECLARATIVE),
            ilFutureDeclarative =  WordTenseModel("할 거야",  Tense.INFORMAL_LOW_FUTURE_DECLARATIVE),

            )
        val target = Tense.FORMAL_LOW_PRESENT_DECLARATIVE
        val input = "해"

        val result =  EnterAnswer.textAnswer(input, targetTense = target, wordModel)
        assertEquals(AnswerResponse.WRONG, result)
    }
}