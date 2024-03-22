package com.jaegerapps.hansan

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaegerapps.hansan.common.components.BottomBarIcon
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.WordModel
import com.jaegerapps.hansan.common.models.WordTenseModel
import com.jaegerapps.hansan.common.models.formalityToStringResource
import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources.icon_list
import hansan.composeapp.generated.resources.icon_mountain
import hansan.composeapp.generated.resources.icon_quotes
import hansan.composeapp.generated.resources.icon_settings
import com.jaegerapps.hansan.core.presentation.HanSanTheme
import com.jaegerapps.hansan.presentation.learn.presentation.components.FormSelectorItem
import com.jaegerapps.hansan.presentation.practice.domain.models.AnswerResponse
import com.jaegerapps.hansan.presentation.practice.presentation.PracticeScreen
import com.jaegerapps.hansan.presentation.practice.presentation.PracticeUiState
import com.jaegerapps.hansan.presentation.practice.presentation.components.CurrentTenseContainer
import com.jaegerapps.hansan.presentation.practice.presentation.components.DropDownContainer
import com.jaegerapps.hansan.presentation.practice.presentation.components.KeyboardEnabledIcon
import com.jaegerapps.hansan.presentation.practice.presentation.components.KeyboardInputContainer
import com.jaegerapps.hansan.presentation.practice.presentation.components.WordContainer
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource


private val hadaWordModel = WordModel(
    dictionaryWord = "하다",
    definition = "to do",
    fhPresentDeclarative = WordTenseModel("합니다", Tense.PRESENT_DECLARATIVE, Formality.FORMAL_HIGH),
    fhPastDeclarative = WordTenseModel("했습니다", Tense.PAST_DECLARATIVE, Formality.FORMAL_HIGH),
    fhFutureDeclarative = WordTenseModel("할 겁니다", Tense.FUTURE_DECLARATIVE, Formality.FORMAL_HIGH),
    flPresentDeclarative = WordTenseModel("해요", Tense.PRESENT_DECLARATIVE, Formality.FORMAL_HIGH),
    flPastDeclarative = WordTenseModel("했어요", Tense.PAST_DECLARATIVE, Formality.FORMAL_HIGH),
    flFutureDeclarative = WordTenseModel("할 거에요", Tense.FUTURE_DECLARATIVE, Formality.FORMAL_HIGH),
    ilPresentDeclarative = WordTenseModel("해", Tense.PRESENT_DECLARATIVE, Formality.FORMAL_HIGH),
    ilPastDeclarative = WordTenseModel("했어", Tense.PAST_DECLARATIVE, Formality.FORMAL_HIGH),
    ilFutureDeclarative = WordTenseModel("할 거야", Tense.FUTURE_DECLARATIVE, Formality.FORMAL_HIGH),

    )
private val tenseModel = TenseModel(
    tense = Tense.PRESENT_DECLARATIVE,
    formality = Formality.FORMAL_HIGH,
    conjugation = "~ㅂ니다/습니다",
    explanation = "Attach \"~ㅂ니다\" to vowel-ending stems or \"~습니다\" to consonant-ending stems, replacing \"다.\"",
    exampleGada = "가다 -> 가 -> 갑니다",
    exampleBoda = "보다 -> 보 -> 봅니다",
    exampleMokda = "먹다 -> 먹 -> 먹습니다",
    exampleHada = "하다 -> 하 -> 합니다"
)

@Preview
@Composable
fun Preview_WordContainer() {
    var word by remember { mutableStateOf("하다") }
    var wrongAnswer by remember { mutableStateOf<AnswerResponse?>(null) }
    HanSanTheme(false) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //Testing the new word animation
            WordContainer(
                word = word,
                definition = "to do",
                answerResponse = null,
                onEvent = {}
            )
            TextButton(onClick = {
                word = if (word == "하다") "가다" else "하다"

            }) {
                Text(text = "Animate above")
            }
            //Testing the wrong answer animation
            WordContainer(

                word = "하다",
                definition = "to do",
                answerResponse = wrongAnswer,
                onEvent = {
                    wrongAnswer = null
                }
            )
            TextButton(onClick = {
                wrongAnswer = if (wrongAnswer == null) AnswerResponse.WRONG else null
            }) {
                Text(text = "Animate above")
            }
        }
    }
}

@Preview
@Composable
fun Preview_TargetContainer() {
    var expanded by remember {
        mutableStateOf(false)
    }
    HanSanTheme(false) {
        Column(modifier = Modifier.padding(12.dp)) {
            DropDownContainer(
                list = listOf("Verbs", "Adjectives", "Adverbs"),
                selected = "Verbs",
                expanded = false,
                onSelect = {

                },
                onExpand = {
                    expanded = !expanded
                }
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                DropDownContainer(
                    list = listOf("Verbs", "Adjectives", "Adverbs"),
                    selected = "Verbs",
                    expanded = false,
                    onSelect = {

                    },
                    onExpand = {
                        expanded = !expanded
                    }
                )
                DropDownContainer(
                    list = listOf("Informal", "Formal low", "Formal High"),
                    selected = "Formal low",
                    expanded = expanded,
                    onSelect = {

                    },
                    onExpand = {
                        expanded = !expanded
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview_CurrentTenseContainer() {
    var expanded by remember {
        mutableStateOf(false)
    }
    HanSanTheme(false) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CurrentTenseContainer(
                expanded = expanded,
                onClick = { expanded = !expanded },
                tense = TenseModel(
                    tense = Tense.PRESENT_DECLARATIVE,
                    formality = Formality.FORMAL_HIGH,
                    conjugation = "~ㅂ니다/습니다",
                    explanation = "Attach \"~ㅂ니다\" to vowel-ending stems or \"~습니다\" to consonant-ending stems, replacing \"다.\"",
                    exampleGada = "가다 -> 가 -> 갑니다",
                    exampleBoda = "보다 -> 보 -> 봅니다",
                    exampleMokda = "먹다 -> 먹 -> 먹습니다",
                    exampleHada = "하다 -> 하 -> 합니다"
                )
            )
            Spacer(Modifier.height(36.dp))
            CurrentTenseContainer(
                expanded = false,
                onClick = {},
                tense = TenseModel(
                    tense = Tense.PRESENT_DECLARATIVE,
                    formality = Formality.FORMAL_LOW,
                    conjugation = "아/어/여~요",
                    explanation = "Last vowel ㅗ/ㅏ - 아요 \n" +
                            "Last letter NOT ㅗ/ㅏ - 어요 \n" +
                            "For 하다 verbs - 여요",
                    exampleGada = "가다 -> 가 -> 가요",
                    exampleBoda = "보다 -> 보 -> 봐요",
                    exampleMokda = "먹다 -> 먹 -> 먹어요",
                    exampleHada = "하다 -> 하 -> 해요"
                )
            )
        }
    }
}

@Preview
@Composable
fun Preview_KeyboardInputContainer() {
    var value by remember {
        mutableStateOf("")
    }
    HanSanTheme(false) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KeyboardInputContainer(
                modifier = Modifier.fillMaxWidth(0.6f),
                input = value,
                onEvent = {
                    value = "it"
                }
            )
        }
    }
}

@Preview
@Composable
fun Preview_KeyboardEnabledIcon() {
    var value by remember {
        mutableStateOf(false)
    }
    HanSanTheme(false) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KeyboardEnabledIcon(
                enabled = value,
                onClick = {
                    value = !value
                }
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Preview
@Composable
fun Preview_BottomBarIcon() {
    var value by remember {
        mutableStateOf("Practice")
    }
    HanSanTheme(false) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BottomBarIcon(
                modifier = Modifier.weight(1f),
                icon = Res.drawable.icon_mountain,
                text = "Practice",
                selected = value == "Practice",
                onClick = {
                    value = "Practice"
                }
            )
            BottomBarIcon(
                modifier = Modifier.weight(1f),

                icon = Res.drawable.icon_list,
                text = "Words",
                selected = value == "Words",
                onClick = {
                    value = "Words"
                }
            )
            BottomBarIcon(
                modifier = Modifier.weight(1f),
                icon = Res.drawable.icon_quotes,
                text = "Learn",
                selected = value == "Learn",
                onClick = {
                    value = "Learn"
                }
            )
            BottomBarIcon(
                modifier = Modifier.weight(1f),
                icon = Res.drawable.icon_settings,
                text = "Settings",
                selected = value == "Settings",
                onClick = {
                    value = "Settings"
                }
            )
        }
    }
}


@Preview
@Composable
fun Preview_PracticeScreen() {
    val state = PracticeUiState(
        currentWord = hadaWordModel,
        targetTense = tenseModel,
    )
    HanSanTheme(false) {
        PracticeScreen(state, {})
    }
}

@Preview
@Composable
fun Preview_PracticeScreenDark() {
    var state = remember {
        mutableStateOf(
            PracticeUiState(
                currentWord = hadaWordModel,
                targetTense = tenseModel,
            )
        )
    }
    var expanded by remember { mutableStateOf(false) }
    HanSanTheme(true) {
        PracticeScreen(state.value) {
            expanded = !expanded
            state.value = state.value.copy(
                tenseExplanationExpanded = expanded
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Preview
@Composable
fun Preview_FormSelectorItem() {
    val list = listOf(
        Formality.FORMAL_HIGH,
        Formality.FORMAL_LOW,
        Formality.INFORMAL_LOW
    )
    var select by remember { mutableStateOf(Formality.FORMAL_HIGH) }
    HanSanTheme(false) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FormSelectorItem(
                text = stringResource(formalityToStringResource(Formality.FORMAL_HIGH)),
                selected = false,
                onSelect = {

                }
            )
            FormSelectorItem(
                text = stringResource(formalityToStringResource(Formality.FORMAL_HIGH)),
                selected = true,
                onSelect = {

                }
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                list.forEach {
                    val weight by animateFloatAsState(targetValue = if (it == select) 1f else 0.8f, tween())
                    FormSelectorItem(
                        modifier = Modifier.weight(weight),
                        text = stringResource(formalityToStringResource(it)),
                        selected = it == select,
                        onSelect = {
                            if (select != it) {
                                select = it
                            }
                        }
                    )
                }
            }
        }
    }
}