package com.jaegarapps.hansan.practice.domain

class EnterAnswerTest {

   /* @Test
    fun `Test FORMAL_HIGH_PRESENT_DECLARATIVE expect answer CORRECT`() {
        val wordModel = VerbModel(
            baseWord = "하다",
            definitionTranslations = "to do",
            type = ModifierType.VERBS,
            irregular = false,
            fhPresentDeclarative = WordTenseModel(
                "합니다",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            fhPastDeclarative = WordTenseModel(
                "했습니다",
                Tense.PAST_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            fhFutureDeclarative = WordTenseModel(
                "할 겁니다",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            flPresentDeclarative = WordTenseModel(
                "해요",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.FORMAL_LOW
            ),
            flPastDeclarative = WordTenseModel("했어요", Tense.PAST_DECLARATIVE, FormalityType.FORMAL_LOW),
            flFutureDeclarative = WordTenseModel(
                "할 거에요",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.FORMAL_LOW
            ),
            ilPresentDeclarative = WordTenseModel(
                "해",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),
            ilPastDeclarative = WordTenseModel(
                "했어",
                Tense.PAST_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),
            ilFutureDeclarative = WordTenseModel(
                "할 거야",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),

            )
        val target = Tense.PRESENT_DECLARATIVE
        val input = "합니다"

        val result = EnterAnswer.textAnswer(
            input,
            targetTense = target,
            formalityType = FormalityType.FORMAL_HIGH,
            wordModel
        )
        assertEquals(AnswerResponse.CORRECT, result)
    }

    @Test
    fun `Test FORMAL_HIGH_PRESENT_DECLARATIVE expect answer WRONG`() {
        val wordModel = VerbModel(
            baseWord = "하다",
            definitionTranslations = "to do",
            type = ModifierType.VERBS,
            irregular = false,
            fhPresentDeclarative = WordTenseModel(
                "합니다",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            fhPastDeclarative = WordTenseModel(
                "했습니다",
                Tense.PAST_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            fhFutureDeclarative = WordTenseModel(
                "할 겁니다",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            flPresentDeclarative = WordTenseModel(
                "해요",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.FORMAL_LOW
            ),
            flPastDeclarative = WordTenseModel("했어요", Tense.PAST_DECLARATIVE, FormalityType.FORMAL_LOW),
            flFutureDeclarative = WordTenseModel(
                "할 거에요",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.FORMAL_LOW
            ),
            ilPresentDeclarative = WordTenseModel(
                "해",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),
            ilPastDeclarative = WordTenseModel(
                "했어",
                Tense.PAST_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),
            ilFutureDeclarative = WordTenseModel(
                "할 거야",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),

            )
        val target = Tense.PRESENT_DECLARATIVE
        val input = "합니디"

        val result = EnterAnswer.textAnswer(
            input,
            targetTense = target,
            formalityType = FormalityType.FORMAL_HIGH,
            wordModel
        )
        assertEquals(AnswerResponse.WRONG, result)
    }

    @Test
    fun `Test blank FORMAL_HIGH_PRESENT_DECLARATIVE expect answer WRONG`() {
        val wordModel = VerbModel(
            baseWord = "하다",
            definitionTranslations = "to do",
            type = ModifierType.VERBS,
            irregular = false,
            fhPresentDeclarative = WordTenseModel(
                "합니다",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            fhPastDeclarative = WordTenseModel(
                "했습니다",
                Tense.PAST_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            fhFutureDeclarative = WordTenseModel(
                "할 겁니다",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            flPresentDeclarative = WordTenseModel(
                "해요",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.FORMAL_LOW
            ),
            flPastDeclarative = WordTenseModel("했어요", Tense.PAST_DECLARATIVE, FormalityType.FORMAL_LOW),
            flFutureDeclarative = WordTenseModel(
                "할 거에요",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.FORMAL_LOW
            ),
            ilPresentDeclarative = WordTenseModel(
                "해",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),
            ilPastDeclarative = WordTenseModel(
                "했어",
                Tense.PAST_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),
            ilFutureDeclarative = WordTenseModel(
                "할 거야",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),

            )
        val target = Tense.PRESENT_DECLARATIVE
        val input = ""

        val result = EnterAnswer.textAnswer(
            input,
            targetTense = target,
            formalityType = FormalityType.FORMAL_HIGH,
            wordModel
        )
        assertEquals(AnswerResponse.WRONG, result)
    }

    @Test
    fun `Test wrong answer INFORMAL_LOW_PRESENT_DECLARATIVE expect answer WRONG`() {
        val wordModel = VerbModel(
            baseWord = "하다",
            definitionTranslations = "to do",
            type = ModifierType.VERBS,
            irregular = false,
            fhPresentDeclarative = WordTenseModel(
                "합니다",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            fhPastDeclarative = WordTenseModel(
                "했습니다",
                Tense.PAST_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            fhFutureDeclarative = WordTenseModel(
                "할 겁니다",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            flPresentDeclarative = WordTenseModel(
                "해요",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.FORMAL_LOW
            ),
            flPastDeclarative = WordTenseModel("했어요", Tense.PAST_DECLARATIVE, FormalityType.FORMAL_LOW),
            flFutureDeclarative = WordTenseModel(
                "할 거에요",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.FORMAL_LOW
            ),
            ilPresentDeclarative = WordTenseModel(
                "해",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),
            ilPastDeclarative = WordTenseModel(
                "했어",
                Tense.PAST_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),
            ilFutureDeclarative = WordTenseModel(
                "할 거야",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),

            )
        val target = Tense.PRESENT_DECLARATIVE
        val input = "해요"

        val result = EnterAnswer.textAnswer(
            input,
            targetTense = target,
            formalityType = FormalityType.FORMAL_HIGH,
            wordModel = wordModel
        )
        assertEquals(AnswerResponse.WRONG, result)
    }

    @Test
    fun `Test wrong answer FORMAL_LOW_PRESENT_DECLARATIVE expect answer WRONG`() {
        val wordModel = VerbModel(
            baseWord = "하다",
            definitionTranslations = "to do",
            type = ModifierType.VERBS,
            irregular = false,
            fhPresentDeclarative = WordTenseModel(
                "합니다",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            fhPastDeclarative = WordTenseModel(
                "했습니다",
                Tense.PAST_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            fhFutureDeclarative = WordTenseModel(
                "할 겁니다",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            flPresentDeclarative = WordTenseModel(
                "해요",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.FORMAL_LOW
            ),
            flPastDeclarative = WordTenseModel("했어요", Tense.PAST_DECLARATIVE, FormalityType.FORMAL_LOW),
            flFutureDeclarative = WordTenseModel(
                "할 거에요",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.FORMAL_LOW
            ),
            ilPresentDeclarative = WordTenseModel(
                "해",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),
            ilPastDeclarative = WordTenseModel(
                "했어",
                Tense.PAST_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),
            ilFutureDeclarative = WordTenseModel(
                "할 거야",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),

            )
        val target = Tense.PRESENT_DECLARATIVE
        val input = "해"

        val result = EnterAnswer.textAnswer(
            input,
            targetTense = target,
            formalityType = FormalityType.FORMAL_HIGH,
            wordModel
        )
        assertEquals(AnswerResponse.WRONG, result)
    }

    @Test
    fun `Test correct answer FORMAL_LOW_PRESENT_DECLARATIVE expect answer CORRECT`() {
        val wordModel = VerbModel(
            baseWord = "하다",
            definitionTranslations = "to do",
            type = ModifierType.VERBS,
            irregular = false,
            fhPresentDeclarative = WordTenseModel(
                "합니다",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            fhPastDeclarative = WordTenseModel(
                "했습니다",
                Tense.PAST_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            fhFutureDeclarative = WordTenseModel(
                "할 겁니다",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.FORMAL_HIGH
            ),
            flPresentDeclarative = WordTenseModel(
                "해요",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.FORMAL_LOW
            ),
            flPastDeclarative = WordTenseModel("했어요", Tense.PAST_DECLARATIVE, FormalityType.FORMAL_LOW),
            flFutureDeclarative = WordTenseModel(
                "할 거에요",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.FORMAL_LOW
            ),
            ilPresentDeclarative = WordTenseModel(
                "해",
                Tense.PRESENT_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),
            ilPastDeclarative = WordTenseModel(
                "했어",
                Tense.PAST_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),
            ilFutureDeclarative = WordTenseModel(
                "할 거야",
                Tense.FUTURE_DECLARATIVE,
                FormalityType.INFORMAL_LOW
            ),

            )
        val target = Tense.PAST_DECLARATIVE
        val input = "했어"

        val result = EnterAnswer.textAnswer(
            input,
            targetTense = target,
            formalityType = FormalityType.INFORMAL_LOW,
            wordModel
        )
        assertEquals(AnswerResponse.CORRECT, result)
    }*/
}