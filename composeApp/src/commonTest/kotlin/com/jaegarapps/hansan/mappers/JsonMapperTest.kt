package com.jaegarapps.hansan.mappers

import com.jaegerapps.hansan.common.mappers.parseJsonWord
import com.jaegerapps.hansan.common.models.ConjugationDto
import com.jaegerapps.hansan.common.models.FormalitiesDto
import com.jaegerapps.hansan.common.models.FormalityContainerDto
import com.jaegerapps.hansan.common.models.FutureDto
import com.jaegerapps.hansan.common.models.PastDto
import com.jaegerapps.hansan.common.models.PresentDto
import com.jaegerapps.hansan.common.models.TranslationDto
import com.jaegerapps.hansan.common.models.VerbDto
import com.jaegerapps.hansan.common.models.WordDto
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonMapperTest {

    @Test
    fun `Test parseJsonWord`() {
        val result = parseJsonWord(jsonData)
        println("Here is the data :D $result")
        assertEquals(expectedResult, result)
    }
}

private val jsonData = """ [
    {
      "id": 1,
      "base": "가다",
      "translations": [
        {
          "language_code": "en",
          "language_translation": "to go"
        },
        {
          "language_code": "es",
          "language_translation": "ir"
        },
        {
          "language_code": "fr",
          "language_translation": "aller"
        },
        {
          "language_code": "de",
          "language_translation": "gehen"
        }
      ],
      "formalities": {
        "formal_high": {
          "conjugations": {
            "present": {
              "declarative": {
                "conjugated": "갑니다",
                "irregular": false
              }
            },
            "past": {
              "declarative": {
                "conjugated": "갔습니다",
                "irregular": false
              }
            },
            "future": {
              "declarative": {
                "conjugated": "갈 겁니다",
                "irregular": false
              }
            }
          }
        },
        "formal_low": {
          "conjugations": {
            "present": {
              "declarative": {
                "conjugated": "가요",
                "irregular": false
              }
            },
            "past": {
              "declarative": {
                "conjugated": "갔어요",
                "irregular": false
              }
            },
            "future": {
              "declarative": {
                "conjugated": "갈 거예요",
                "irregular": false
              }
            }
          }
        },
        "informal_low": {
          "conjugations": {
            "present": {
              "declarative": {
                "conjugated": "가",
                "irregular": false
              }
            },
            "past": {
              "declarative": {
                "conjugated": "갔어",
                "irregular": false
              }
            },
            "future": {
              "declarative": {
                "conjugated": "갈 거야",
                "irregular": false
              }
            }
          }
        }
      }
    },
    {
      "id": 2,
      "base": "가르치다",
      "translations": [
        {
          "language_code": "en",
          "language_translation": "to teach"
        },
        {
          "language_code": "es",
          "language_translation": "enseñar"
        },
        {
          "language_code": "fr",
          "language_translation": "enseigner"
        },
        {
          "language_code": "de",
          "language_translation": "Lehren"
        }
      ],
      "formalities": {
        "formal_high": {
          "conjugations": {
            "present": {
              "declarative": {
                "conjugated": "가르칩니다",
                "irregular": false
              }
            },
            "past": {
              "declarative": {
                "conjugated": "가르쳤습니다",
                "irregular": false
              }
            },
            "future": {
              "declarative": {
                "conjugated": "가르칠 겁니다",
                "irregular": false
              }
            }
          }
        },
        "formal_low": {
          "conjugations": {
            "present": {
              "declarative": {
                "conjugated": "가르쳐요",
                "irregular": false
              }
            },
            "past": {
              "declarative": {
                "conjugated": "가르쳤어요",
                "irregular": false
              }
            },
            "future": {
              "declarative": {
                "conjugated": "가르칠 게예요",
                "irregular": false
              }
            }
          }
        },
        "informal_low": {
          "conjugations": {
            "present": {
              "declarative": {
                "conjugated": "가르쳐",
                "irregular": false
              }
            },
            "past": {
              "declarative": {
                "conjugated": "가르쳤어",
                "irregular": false
              }
            },
            "future": {
              "declarative": {
                "conjugated": "가르칠 거야",
                "irregular": false
              }
            }
          }
        }
      }
    }
  ]

""".trimIndent()

val expectedResult = listOf(
    VerbDto(
        id = 1,
        base = "가다",
        translationDto = listOf(
            TranslationDto(
                language_code = "en",
                language_translation = "to go"
            ),
            TranslationDto(
                language_code = "es",
                language_translation = "ir"
            ),
            TranslationDto(
                language_code = "fr",
                language_translation = "aller"
            ),
            TranslationDto(
                language_code = "de",
                language_translation = "gehen"
            )
        ),
        formalitiesDto = FormalitiesDto(
            formal_high = FormalityContainerDto(
                conjugations = ConjugationDto(
                    present = PresentDto(
                        declarative = WordDto(
                            conjugated = "갑니다",
                            irregular = false
                        )
                    ),
                    past = PastDto(declarative = WordDto(conjugated = "갔습니다", irregular = false)),
                    future = FutureDto(
                        declarative = WordDto(
                            conjugated = "갈 겁니다",
                            irregular = false
                        )
                    )
                )
            ),
            formal_low = FormalityContainerDto(
                conjugations = ConjugationDto(
                    present = PresentDto(
                        declarative = WordDto(
                            conjugated = "가요",
                            irregular = false
                        )
                    ),
                    past = PastDto(declarative = WordDto(conjugated = "갔어요", irregular = false)),
                    future = FutureDto(
                        declarative = WordDto(
                            conjugated = "갈 거예요",
                            irregular = false
                        )
                    )
                )
            ),
            informal_low = FormalityContainerDto(
                conjugations = ConjugationDto(
                    present = PresentDto(
                        declarative = WordDto(
                            conjugated = "가",
                            irregular = false
                        )
                    ),
                    past = PastDto(declarative = WordDto(conjugated = "갔어", irregular = false)),
                    future = FutureDto(
                        declarative = WordDto(
                            conjugated = "갈 거야",
                            irregular = false
                        )
                    )
                )
            )
        )
    ), VerbDto(
        id = 2,
        base = "가르치다",
        translationDto = listOf(
            TranslationDto(
                language_code = "en",
                language_translation = "to teach"
            ),
            TranslationDto(
                language_code = "es",
                language_translation = "enseñar"
            ),
            TranslationDto(
                language_code = "fr",
                language_translation = "enseigner"
            ),
            TranslationDto(
                language_code = "de",
                language_translation = "Lehren"
            )
        ),
        formalitiesDto = FormalitiesDto(
            formal_high = FormalityContainerDto(
                conjugations = ConjugationDto(
                    present = PresentDto(
                        declarative = WordDto(
                            conjugated = "가르칩니다",
                            irregular = false
                        )
                    ),
                    past = PastDto(declarative = WordDto(conjugated = "가르쳤습니다", irregular = false)),
                    future = FutureDto(
                        declarative = WordDto(
                            conjugated = "가르칠 겁니다",
                            irregular = false
                        )
                    )
                )
            ),
            formal_low = FormalityContainerDto(
                conjugations = ConjugationDto(
                    present = PresentDto(
                        declarative = WordDto(
                            conjugated = "가르쳐요",
                            irregular = false
                        )
                    ),
                    past = PastDto(declarative = WordDto(conjugated = "가르쳤어요", irregular = false)),
                    future = FutureDto(
                        declarative = WordDto(
                            conjugated = "가르칠 게예요",
                            irregular = false
                        )
                    )
                )
            ),
            informal_low = FormalityContainerDto(
                conjugations = ConjugationDto(
                    present = PresentDto(
                        declarative = WordDto(
                            conjugated = "가르쳐",
                            irregular = false
                        )
                    ),
                    past = PastDto(declarative = WordDto(conjugated = "가르쳤어", irregular = false)),
                    future = FutureDto(
                        declarative = WordDto(
                            conjugated = "가르칠 거야",
                            irregular = false
                        )
                    )
                )
            )
        )
    )
)