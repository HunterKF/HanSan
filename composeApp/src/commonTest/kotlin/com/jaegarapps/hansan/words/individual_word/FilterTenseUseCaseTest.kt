package com.jaegarapps.hansan.words.individual_word

import com.jaegerapps.hansan.common.models.Category
import com.jaegerapps.hansan.common.models.DefinitionTranslation
import com.jaegerapps.hansan.common.models.DetailedTense
import com.jaegerapps.hansan.common.models.Formalities
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.VerbModel
import com.jaegerapps.hansan.common.models.Word
import com.jaegerapps.hansan.screens.practice.domain.models.Level
import com.jaegerapps.hansan.screens.words.word_individual.domain.use_case.FilterTenseUseCase
import kotlin.test.Test

class FilterTenseUseCaseTest {
    private val verbModel =
        VerbModel(
            baseWord = "가다",
            definitionTranslations = listOf(
                DefinitionTranslation(
                    languageCode = "en",
                    translation = "to go"
                ),
                DefinitionTranslation(
                    languageCode = "es",
                    translation = "ir"
                ),
                DefinitionTranslation(
                    languageCode = "fr",
                    translation = "aller"
                ),
                DefinitionTranslation(
                    languageCode = "de",
                    translation = "gehen"
                )
            ),
            formalities = Formalities(
                formalHigh = Formality(
                    type = FormalityType.FORMAL_HIGH,
                    conjugation = listOf(
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.PRESENT_DECLARATIVE_FORMAL_HIGH,
                            formality = FormalityType.FORMAL_HIGH,
                            conjugatedWord = "갑니다",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.PRESENT_PROGRESSIVE_FORMAL_HIGH,
                            formality = FormalityType.FORMAL_HIGH,
                            conjugatedWord = "가고 있습니다",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.PAST_DECLARATIVE_FORMAL_HIGH,
                            formality = FormalityType.FORMAL_HIGH,
                            conjugatedWord = "갔습니다",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.PAST_PROGRESSIVE_FORMAL_HIGH,
                            formality = FormalityType.FORMAL_HIGH,
                            conjugatedWord = "가고 있었습니다",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.FUTURE_DECLARATIVE_FORMAL_HIGH,
                            formality = FormalityType.FORMAL_HIGH,
                            conjugatedWord = "갈 겁니다",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.FUTURE_PROGRESSIVE_FORMAL_HIGH,
                            formality = FormalityType.FORMAL_HIGH,
                            conjugatedWord = "가고 있을 겁니다",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.OTHER_PROPOSITIVE_FORMAL_HIGH,
                            formality = FormalityType.FORMAL_HIGH,
                            conjugatedWord = "갑시다",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.OTHER_DESIRE_FORMAL_HIGH,
                            formality = FormalityType.FORMAL_HIGH,
                            conjugatedWord = "가고 싶습니다",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.OTHER_NECESSITY_FORMAL_HIGH,
                            formality = FormalityType.FORMAL_HIGH,
                            conjugatedWord = "가야 합니다",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.OTHER_POTENTIAL_FORMAL_HIGH,
                            formality = FormalityType.FORMAL_HIGH,
                            conjugatedWord = "갈 수 있습니다",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.OTHER_PERMISSION_FORMAL_HIGH,
                            formality = FormalityType.FORMAL_HIGH,
                            conjugatedWord = "가도 됩니다",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.OTHER_PROHIBITION_FORMAL_HIGH,
                            formality = FormalityType.FORMAL_HIGH,
                            conjugatedWord = "가면 안 됩니다",
                            irregular = false
                        ),
                    )
                ),
                formalLow = Formality(
                    type = FormalityType.FORMAL_LOW,
                    conjugation = listOf(
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.PRESENT_DECLARATIVE_FORMAL_LOW,
                            formality = FormalityType.FORMAL_LOW,
                            conjugatedWord = "가요",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.PRESENT_PROGRESSIVE_FORMAL_LOW,
                            formality = FormalityType.FORMAL_LOW,
                            conjugatedWord = "가고 있어요",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.OTHER_PROPOSITIVE_FORMAL_LOW,
                            formality = FormalityType.FORMAL_LOW,
                            conjugatedWord = "갈까요?",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.PAST_DECLARATIVE_FORMAL_LOW,
                            formality = FormalityType.FORMAL_LOW,
                            conjugatedWord = "갔어요",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.PAST_PROGRESSIVE_FORMAL_LOW,
                            formality = FormalityType.FORMAL_LOW,
                            conjugatedWord = "가고 있었어요",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.FUTURE_DECLARATIVE_FORMAL_LOW,
                            formality = FormalityType.FORMAL_LOW,
                            conjugatedWord = "갈 거예요",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.FUTURE_PROGRESSIVE_FORMAL_LOW,
                            formality = FormalityType.FORMAL_LOW,
                            conjugatedWord = "가고 있을 거예요",
                            irregular = false
                        )
                    )
                ),
                informalLow = Formality(
                    type = FormalityType.INFORMAL_LOW,
                    conjugation = listOf(
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.PRESENT_DECLARATIVE_INFORMAL,
                            formality = FormalityType.INFORMAL_LOW,
                            conjugatedWord = "가",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.PRESENT_PROGRESSIVE_INFORMAL_LOW,
                            formality = FormalityType.INFORMAL_LOW,
                            conjugatedWord = "가고 있어",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.OTHER_PROPOSITIVE_INFORMAL_LOW,
                            formality = FormalityType.INFORMAL_LOW,
                            conjugatedWord = "가자",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.PAST_DECLARATIVE_INFORMAL,
                            formality = FormalityType.INFORMAL_LOW,
                            conjugatedWord = "갔어",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.PAST_PROGRESSIVE_INFORMAL,
                            formality = FormalityType.INFORMAL_LOW,
                            conjugatedWord = "가고 있었어",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.FUTURE_DECLARATIVE_INFORMAL,
                            formality = FormalityType.INFORMAL_LOW,
                            conjugatedWord = "갈 거야",
                            irregular = false
                        ),
                        Word(
                            level = Level.LEVEL_ONE,
                            dateExpire = null,
                            detailedTense = DetailedTense.FUTURE_PROGRESSIVE_INFORMAL,
                            formality = FormalityType.INFORMAL_LOW,
                            conjugatedWord = "가고 있을 거야",
                            irregular = false
                        )
                    )
                ),

            )
        )

    @Test
    fun `Return all present tense only`() {
        val presentTense = FilterTenseUseCase.filterTense(verbModel, category = Category.PRESENT)
        println(presentTense)
    }

    @Test
    fun `Return all future tense only`() {
        val presentTense = FilterTenseUseCase.filterTense(verbModel, category = Category.FUTURE)
        println(presentTense)
    }

    @Test
    fun `Return all past tense only`() {
        val presentTense = FilterTenseUseCase.filterTense(verbModel, category = Category.PAST)
        println(presentTense)
    }

    @Test
    fun `Return all other tense only`() {
        val presentTense = FilterTenseUseCase.filterTense(verbModel, category = Category.OTHER)
        println(presentTense)
    }

}