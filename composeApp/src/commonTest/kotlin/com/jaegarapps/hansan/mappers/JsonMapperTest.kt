package com.jaegarapps.hansan.mappers

import com.jaegerapps.hansan.common.mappers.parseJsonWord
import com.jaegerapps.hansan.common.models.ConjugationDto
import com.jaegerapps.hansan.common.models.FormalitiesEntity
import com.jaegerapps.hansan.common.models.FormalityContainerDto
import com.jaegerapps.hansan.common.models.FutureDto
import com.jaegerapps.hansan.common.models.PastDto
import com.jaegerapps.hansan.common.models.PresentDto
import com.jaegerapps.hansan.common.models.TranslationsEntity
import com.jaegerapps.hansan.common.models.VerbEntity
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
          "translations": {
            "english": "to go"
          },
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
          "translations": {
            "english": "to teach"
          },
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
    VerbEntity(
        id = 1,
        base = "가다",
        translationsEntity = TranslationsEntity(english = "to go"),
        formalitiesEntity = FormalitiesEntity(
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
    ), VerbEntity(
        id = 2,
        base = "가르치다",
        translationsEntity = TranslationsEntity(english = "to teach"),
        formalitiesEntity = FormalitiesEntity(
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