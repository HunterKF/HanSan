package com.jaegarapps.hansan.mappers

import com.jaegerapps.hansan.common.mappers.parseJsonWord
import com.jaegerapps.hansan.common.models.ConjugationDto
import com.jaegerapps.hansan.common.models.FormalitiesDto
import com.jaegerapps.hansan.common.models.FormalityContainerDto
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
          "present": [
            {
              "tense_name": "present_declarative_formal_high",
              "conjugated": "갑니다",
              "irregular": false
            },
            {
              "tense_name": "present_progressive_formal_high",
              "conjugated": "가고 있습니다",
              "irregular": false
            },
            {
              "tense_name": "propositive_formal_high",
              "conjugated": "갑시다",
              "irregular": false
            }
          ],
          "past": [
            {
              "tense_name": "past_declarative_formal_high",
              "conjugated": "갔습니다",
              "irregular": false
            },
            {
              "tense_name": "past_progressive_formal_high",
              "conjugated": "가고 있었습니다",
              "irregular": false
            }
          ],
          "future": [
            {
              "tense_name": "future_declarative_formal_high",
              "conjugated": "갈 겁니다",
              "irregular": false
            },
            {
              "tense_name": "future_progressive_formal_high",
              "conjugated": "가고 있을 겁니다",
              "irregular": false
            }
          ],
          "other": [
            {
              "tense_name": "imperative_formal_high",
              "conjugated": "가십시오",
              "irregular": false
            },
            {
              "tense_name": "permission_formal_high",
              "conjugated": "가도 됩니다",
              "irregular": false
            },
            {
              "tense_name": "prohibition_formal_high",
              "conjugated": "가면 안 됩니다",
              "irregular": false
            },

            {
              "tense_name": "desire_formal_high",
              "conjugated": "가고 싶습니다",
              "irregular": false
            },

            {
              "tense_name": "necessity_formal_high",
              "conjugated": "가야 합니다",
              "irregular": false
            },
            {
              "tense_name": "potential_formal_high",
              "conjugated": "갈 수 있습니다",
              "irregular": false
            }
          ]
        }
      },
      "formal_low": {
        "conjugations": {
          "present": [
            {
              "tense_name": "present_declarative_formal_low",
              "conjugated": "가요",
              "irregular": false
            },
            {
              "tense_name": "present_progressive_formal_low",
              "conjugated": "가고 있어요",
              "irregular": false
            },
            {
              "tense_name": "propositive_formal_low",
              "conjugated": "갈까요?",
              "irregular": false
            }
          ],
          "past": [
            {
              "tense_name": "past_declarative_formal_low",
              "conjugated": "갔어요",
              "irregular": false
            },
            {
              "tense_name": "past_progressive_formal_low",
              "conjugated": "가고 있었어요",
              "irregular": false
            }
          ],
          "future": [
            {
              "tense_name": "future_declarative_formal_low",
              "conjugated": "갈 거예요",
              "irregular": false
            },
            {
              "tense_name": "future_progressive_formal_low",
              "conjugated": "가고 있을 거예요",
              "irregular": false
            }
          ],
          "other": [
            {
              "tense_name": "imperative_formal_low",
              "conjugated": "가세요",
              "irregular": false
            },
            {
              "tense_name": "permission_formal_low",
              "conjugated": "가도 돼요",
              "irregular": false
            },
            {
              "tense_name": "prohibition_formal_low",
              "conjugated": "가면 안 돼요",
              "irregular": false
            },
            {
              "tense_name": "desire_formal_low",
              "conjugated": "가고 싶어요",
              "irregular": false
            },
            {
              "tense_name": "necessity_formal_low",
              "conjugated": "가야 해요",
              "irregular": false
            },
            {
              "tense_name": "potential_formal_low",
              "conjugated": "갈 수 있어요",
              "irregular": false
            }
          ]
        }
      },
      "informal_low": {
        "conjugations": {
          "present": [
            {
              "tense_name": "present_declarative_informal",
              "conjugated": "가",
              "irregular": false
            },
            {
              "tense_name": "present_progressive_informal_low",
              "conjugated": "가고 있어",
              "irregular": false
            },
            {
              "tense_name": "propositive_informal_low",
              "conjugated": "가자",
              "irregular": false
            }
          ],
          "past": [
            {
              "tense_name": "past_declarative_informal",
              "conjugated": "갔어",
              "irregular": false
            },
            {
              "tense_name": "past_progressive_informal_low",
              "conjugated": "가고 있었어",
              "irregular": false
            }
          ],
          "future": [
            {
              "tense_name": "future_declarative_informal",
              "conjugated": "갈 거야",
              "irregular": false
            },
            {
              "tense_name": "future_progressive_informal_low",
              "conjugated": "가고 있을 거야",
              "irregular": false
            }
          ],
          "other": [
            {
              "tense_name": "imperative_informal_low",
              "conjugated": "가",
              "irregular": false
            },
            {
              "tense_name": "permission_informal_low",
              "conjugated": "가도 돼",
              "irregular": false
            },
            {
              "tense_name": "prohibition_informal_low",
              "conjugated": "가면 안 돼",
              "irregular": false
            },
            {
              "tense_name": "desire_informal_low",
              "conjugated": "가고 싶어",
              "irregular": false
            },
            {
              "tense_name": "necessity_informal_low",
              "conjugated": "가야 해",
              "irregular": false
            },
            {
              "tense_name": "potential_informal_low",
              "conjugated": "갈 수 있어",
              "irregular": false
            }
          ]
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
            TranslationDto(language_code = "en", language_translation = "to go"),
            TranslationDto(language_code = "es", language_translation = "ir"),
            TranslationDto(language_code = "fr", language_translation = "aller"),
            TranslationDto(language_code = "de", language_translation = "gehen")
        ),
        formalitiesDto = FormalitiesDto(
            formal_high = FormalityContainerDto(
                conjugations = ConjugationDto(
                    present = listOf(
                        WordDto(tense_name = "present_declarative_formal_high", conjugated = "갑니다", irregular = false),
                        WordDto(tense_name = "present_progressive_formal_high", conjugated = "가고 있습니다", irregular = false),
                        WordDto(tense_name = "propositive_formal_high", conjugated = "갑시다", irregular = false)
                    ),
                    past = listOf(
                        WordDto(tense_name = "past_declarative_formal_high", conjugated = "갔습니다", irregular = false),
                        WordDto(tense_name = "past_progressive_formal_high", conjugated = "가고 있었습니다", irregular = false)
                    ),
                    future = listOf(
                        WordDto(tense_name = "future_declarative_formal_high", conjugated = "갈 겁니다", irregular = false),
                        WordDto(tense_name = "future_progressive_formal_high", conjugated = "가고 있을 겁니다", irregular = false)
                    ),
                    other = listOf(
                        WordDto(tense_name = "imperative_formal_high", conjugated = "가십시오", irregular = false),
                        WordDto(tense_name = "permission_formal_high", conjugated = "가도 됩니다", irregular = false),
                        WordDto(tense_name = "prohibition_formal_high", conjugated = "가면 안 됩니다", irregular = false),
                        WordDto(tense_name = "desire_formal_high", conjugated = "가고 싶습니다", irregular = false),
                        WordDto(tense_name = "necessity_formal_high", conjugated = "가야 합니다", irregular = false),
                        WordDto(tense_name = "potential_formal_high", conjugated = "갈 수 있습니다", irregular = false)
                    )
                )
            ),
            formal_low = FormalityContainerDto(
                conjugations = ConjugationDto(
                    present = listOf(
                        WordDto(tense_name = "present_declarative_formal_low", conjugated = "가요", irregular = false),
                        WordDto(tense_name = "present_progressive_formal_low", conjugated = "가고 있어요", irregular = false),
                        WordDto(tense_name = "propositive_formal_low", conjugated = "갈까요?", irregular = false)
                    ),
                    past = listOf(
                        WordDto(tense_name = "past_declarative_formal_low", conjugated = "갔어요", irregular = false),
                        WordDto(tense_name = "past_progressive_formal_low", conjugated = "가고 있었어요", irregular = false)
                    ),
                    future = listOf(
                        WordDto(tense_name = "future_declarative_formal_low", conjugated = "갈 거예요", irregular = false),
                        WordDto(tense_name = "future_progressive_formal_low", conjugated = "가고 있을 거예요", irregular = false)
                    ),
                    other = listOf(
                        WordDto(tense_name = "imperative_formal_low", conjugated = "가세요", irregular = false),
                        WordDto(tense_name = "permission_formal_low", conjugated = "가도 돼요", irregular = false),
                        WordDto(tense_name = "prohibition_formal_low", conjugated = "가면 안 돼요", irregular = false),
                        WordDto(tense_name = "desire_formal_low", conjugated = "가고 싶어요", irregular = false),
                        WordDto(tense_name = "necessity_formal_low", conjugated = "가야 해요", irregular = false),
                        WordDto(tense_name = "potential_formal_low", conjugated = "갈 수 있어요", irregular = false)
                    )
                )
            ),
            informal_low = FormalityContainerDto(
                conjugations = ConjugationDto(
                    present = listOf(
                        WordDto(tense_name = "present_declarative_informal", conjugated = "가", irregular = false),
                        WordDto(tense_name = "present_progressive_informal_low", conjugated = "가고 있어", irregular = false),
                        WordDto(tense_name = "propositive_informal_low", conjugated = "가자", irregular = false)
                    ),
                    past = listOf(
                        WordDto(tense_name = "past_declarative_informal", conjugated = "갔어", irregular = false),
                        WordDto(tense_name = "past_progressive_informal_low", conjugated = "가고 있었어", irregular = false)
                    ),
                    future = listOf(
                        WordDto(tense_name = "future_declarative_informal", conjugated = "갈 거야", irregular = false),
                        WordDto(tense_name = "future_progressive_informal_low", conjugated = "가고 있을 거야", irregular = false)
                    ),
                    other = listOf(
                        WordDto(tense_name = "imperative_informal_low", conjugated = "가", irregular = false),
                        WordDto(tense_name = "permission_informal_low", conjugated = "가도 돼", irregular = false),
                        WordDto(tense_name = "prohibition_informal_low", conjugated = "가면 안 돼", irregular = false),
                        WordDto(tense_name = "desire_informal_low", conjugated = "가고 싶어", irregular = false),
                        WordDto(tense_name = "necessity_informal_low", conjugated = "가야 해", irregular = false),
                        WordDto(tense_name = "potential_informal_low", conjugated = "갈 수 있어", irregular = false)
                    )
                )
            )
        )
    )
)
