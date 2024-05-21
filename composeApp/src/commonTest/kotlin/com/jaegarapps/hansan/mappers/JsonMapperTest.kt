package com.jaegarapps.hansan.mappers

import com.jaegerapps.hansan.common.mappers.parseJsonWord
import kotlin.test.Test

class JsonMapperTest {

    @Test
    fun `Test parseJsonWord`() {
        val result = parseJsonWord(jsonData)
        println("Here is the data :D $result")
    }
}

private val jsonData = """
    {
      "verbs": [
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
    }
""".trimIndent()