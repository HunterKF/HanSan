package com.jaegerapps.hansan

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import com.jaegerapps.hansan.common.models.DefinitionTranslations
import com.jaegerapps.hansan.common.models.Formalities
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.VerbModel
import com.jaegerapps.hansan.common.models.Word
import com.jaegerapps.hansan.common.models.getResStringFromFormality
import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources.icon_list
import hansan.composeapp.generated.resources.icon_mountain
import hansan.composeapp.generated.resources.icon_quotes
import hansan.composeapp.generated.resources.icon_settings
import com.jaegerapps.hansan.core.presentation.HanSanTheme
import com.jaegerapps.hansan.screens.learn.presentation.tense_list.LearnScreen
import com.jaegerapps.hansan.screens.learn.presentation.tense_list.LearnUiState
import com.jaegerapps.hansan.screens.learn.presentation.components.FormSelectorItem
import com.jaegerapps.hansan.screens.learn.presentation.individual_tense.components.IrregularContainer
import com.jaegerapps.hansan.screens.learn.presentation.individual_tense.components.IrregularItem
import com.jaegerapps.hansan.screens.learn.presentation.components.LearnTense
import com.jaegerapps.hansan.screens.learn.presentation.components.TenseHeader
import com.jaegerapps.hansan.screens.learn.presentation.individual_tense.IndividualTenseScreen
import com.jaegerapps.hansan.screens.practice.domain.models.AnswerResponse
import com.jaegerapps.hansan.screens.practice.presentation.PracticeErrorMessage
import com.jaegerapps.hansan.screens.practice.presentation.PracticeScreen
import com.jaegerapps.hansan.screens.practice.presentation.PracticeUiState
import com.jaegerapps.hansan.screens.practice.presentation.components.AnswerCard
import com.jaegerapps.hansan.screens.practice.presentation.components.FormalityContainer
import com.jaegerapps.hansan.screens.practice.presentation.components.TargetFormsContainer
import com.jaegerapps.hansan.screens.practice.presentation.components.WordContainer
import com.jaegerapps.hansan.screens.settings.presentation.SettingsScreen
import com.jaegerapps.hansan.screens.settings.presentation.SettingsUiState
import com.jaegerapps.hansan.screens.settings.presentation.components.InputItem
import com.jaegerapps.hansan.screens.settings.presentation.components.ToggleItem
import com.jaegerapps.hansan.screens.words.word_individual.component.ExamineWordContainer
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource


private val hadaWordModel = VerbModel(
    baseWord = "말하다",
    definitionTranslations = DefinitionTranslations(english = "to speak"),
    formalities = Formalities(
        formalHigh = Formality(
            type = FormalityType.FORMAL_HIGH,
            conjugation = listOf(
                /*Word(tense = Tense.PRESENT_DECLARATIVE, conjugatedWord = "말합니다", irregular = false, ),
                Word(tense = Tense.PAST_DECLARATIVE, conjugatedWord = "말했습니다", irregular = false),
                Word(tense = Tense.FUTURE_DECLARATIVE, conjugatedWord = "말할 것입니다", irregular = false)*/
            )
        ),
        formalLow = Formality(
            type = FormalityType.FORMAL_LOW,
            conjugation = listOf(
                /*Word(tense = Tense.PRESENT_DECLARATIVE, conjugatedWord = "말해요", irregular = false),
                Word(tense = Tense.PAST_DECLARATIVE, conjugatedWord = "말했어요", irregular = false),
                Word(tense = Tense.FUTURE_DECLARATIVE, conjugatedWord = "말할 거예요", irregular = false)*/
            )
        ),
        informalLow = Formality(
            type = FormalityType.INFORMAL_LOW,
            conjugation = listOf(
               /* Word(tense = Tense.PRESENT_DECLARATIVE, conjugatedWord = "말해", irregular = false),
                Word(tense = Tense.PAST_DECLARATIVE, conjugatedWord = "말했어", irregular = false),
                Word(tense = Tense.FUTURE_DECLARATIVE, conjugatedWord = "말할 거야", irregular = false)*/
            )
        )
    )
)
private val tenseModel = TenseModel(
    tense = Tense.PAST_DECLARATIVE,
    formalityType = FormalityType.FORMAL_HIGH,
    conjugation = "었/았/였~습니다",
    explanation = "Last vowel 아/오 - 았습니다\n" +
            "Last vowel 어 - 었습니다",
    exampleGada = "가다 -> 가 -> 갔습니다",
    exampleBoda = "보다 -> 보 -> 봤습니다",
    exampleMokda = "먹다 -> 먹 -> 먹었습니다",
    exampleHada = "하다 -> 하 -> 했습니다",
    irregularSieut = "If you combine ‘ㅅ’ irregular verbs with a suffix that starts with a vowel, you drop ‘ㅅ’, then conjugate based on the last vowel.\nFor example, 낫다 - 나았습니다, 짓다 -> 지었습니다",
    irregularDieut = "For irregular ㄷ verbs, no change is made to the 받침 if it is followed by a consonant.\nFor example: 듣다 → 듣습니다\nFor irregular ㄷ verbs, the ㄷ 받침 changes to a ㄹ when followed by a vowel.\nFor example: 듣다 → 들었습니다",
    irregularBieub = "You drop ‘ㅂ’ from the verb stem and add 웠습니다.\nFor example: 굽다 -> 구웠습니다",
    irregularEu = "For ㅡ irregular verbs, use the last vowel and conjugate based on that.\nFor example: 잠그다 -> 잠갔습니다",
    irregularReu = "For 르 irregular verbs, if the last vowel is ㅏ/오, change 르 to ㄹ랐습니다. For other vowels, change ㅡ to ㄹ렀습니다. \\nFor example: 부르다 -> 불렀습니다, 고르다 -> 골랐습니다, 마르다 -> 말랐습니다",
    irregularRieul = null
)

private val tenseModelList = listOf(
    TenseModel(
        tense = Tense.PRESENT_DECLARATIVE,
        formalityType = FormalityType.FORMAL_HIGH,
        conjugation = "~ㅂ니다/습니다",
        explanation = "Attach \"~ㅂ니다\" to vowel-ending stems or \"~습니다\" to consonant-ending stems, replacing \"다.\"",
        exampleGada = "가다 -> 가 -> 갑니다",
        exampleBoda = "보다 -> 보 -> 봅니다",
        exampleMokda = "먹다 -> 먹 -> 먹습니다",
        exampleHada = "하다 -> 하 -> 합니다",
        null,
        null,
        null,
        null,
        null,
        null
    ),
    TenseModel(
        tense = Tense.PAST_DECLARATIVE,
        formalityType = FormalityType.FORMAL_HIGH,
        conjugation = "~ㅂ니다/습니다",
        explanation = "Attach \"~ㅂ니다\" to vowel-ending stems or \"~습니다\" to consonant-ending stems, replacing \"다.\"",
        exampleGada = "가다 -> 가 -> 갑니다",
        exampleBoda = "보다 -> 보 -> 봅니다",
        exampleMokda = "먹다 -> 먹 -> 먹습니다",
        exampleHada = "하다 -> 하 -> 합니다",
        null,
        null,
        null,
        null,
        null,
        null
    ),
    TenseModel(
        tense = Tense.FUTURE_DECLARATIVE,
        formalityType = FormalityType.FORMAL_HIGH,
        conjugation = "~ㅂ니다/습니다",
        explanation = "Attach \"~ㅂ니다\" to vowel-ending stems or \"~습니다\" to consonant-ending stems, replacing \"다.\"",
        exampleGada = "가다 -> 가 -> 갑니다",
        exampleBoda = "보다 -> 보 -> 봅니다",
        exampleMokda = "먹다 -> 먹 -> 먹습니다",
        exampleHada = "하다 -> 하 -> 합니다",
        null,
        null,
        null,
        null,
        null,
        null
    )
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
fun Preview_CurrentTenseContainer() {
    var expanded by remember {
        mutableStateOf(false)
    }
    HanSanTheme(false) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TargetFormsContainer(
                expanded = expanded,
                onClick = { expanded = !expanded },
                tense = TenseModel(
                    tense = Tense.PRESENT_DECLARATIVE,
                    formalityType = FormalityType.FORMAL_HIGH,
                    conjugation = "~ㅂ니다/습니다",
                    explanation = "Attach \"~ㅂ니다\" to vowel-ending stems or \"~습니다\" to consonant-ending stems, replacing \"다.\"",
                    exampleGada = "가다 -> 가 -> 갑니다",
                    exampleBoda = "보다 -> 보 -> 봅니다",
                    exampleMokda = "먹다 -> 먹 -> 먹습니다",
                    exampleHada = "하다 -> 하 -> 합니다",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            Spacer(Modifier.height(36.dp))
            TargetFormsContainer(
                expanded = false,
                onClick = {},
                tense = TenseModel(
                    tense = Tense.PRESENT_DECLARATIVE,
                    formalityType = FormalityType.FORMAL_LOW,
                    conjugation = "아/어/여~요",
                    explanation = "Last vowel ㅗ/ㅏ - 아요 \n" +
                            "Last letter NOT ㅗ/ㅏ - 어요 \n" +
                            "For 하다 verbs - 여요",
                    exampleGada = "가다 -> 가 -> 가요",
                    exampleBoda = "보다 -> 보 -> 봐요",
                    exampleMokda = "먹다 -> 먹 -> 먹어요",
                    exampleHada = "하다 -> 하 -> 해요",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
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
        currentVerb = hadaWordModel,
        targetTense = tenseModel,
    )
    HanSanTheme(false) {
        PracticeScreen(state, {})
    }
}

@Preview
@Composable
fun Preview_PracticeScreenErrorMessage() {
    var errorMessage: PracticeErrorMessage? by remember { mutableStateOf(null) }
    val state = PracticeUiState(
        currentVerb = hadaWordModel,
        targetTense = tenseModel,
        errorMessage = errorMessage
    )

    HanSanTheme(false) {
        PracticeScreen(state) {
            errorMessage = if (errorMessage != null) null else PracticeErrorMessage.NOT_KOREAN
        }
    }
}

@Preview
@Composable
fun Preview_PracticeScreenDark() {
    var state = remember {
        mutableStateOf(
            PracticeUiState(
                currentVerb = hadaWordModel,
                targetTense = tenseModel,
            )
        )
    }
    var expanded by remember { mutableStateOf(false) }
    HanSanTheme(true) {
        PracticeScreen(state.value) {
            expanded = !expanded

        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Preview
@Composable
fun Preview_FormSelectorItem() {
    val list = listOf(
        FormalityType.FORMAL_HIGH,
        FormalityType.FORMAL_LOW,
        FormalityType.INFORMAL_LOW
    )
    var select by remember { mutableStateOf(FormalityType.FORMAL_HIGH) }
    HanSanTheme(false) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FormSelectorItem(
                text = stringResource(getResStringFromFormality(FormalityType.FORMAL_HIGH)),
                selected = false,
                onSelect = {

                }
            )
            FormSelectorItem(
                text = stringResource(getResStringFromFormality(FormalityType.FORMAL_HIGH)),
                selected = true,
                onSelect = {

                }
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                list.forEach {
                    val weight by animateFloatAsState(
                        targetValue = if (it == select) 1f else 0.8f,
                        tween()
                    )
                    FormSelectorItem(
                        modifier = Modifier.weight(weight),
                        text = stringResource(getResStringFromFormality(it)),
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

@Preview
@Composable
fun Preview_LearnTense() {
    var expanded by remember { mutableStateOf(false) }
    HanSanTheme(false) {
        Column {

            LearnTense(
                tenseModel = tenseModel,
                expanded = false,
                onClick = {}
            )

            Spacer(Modifier.height(12.dp))
            LearnTense(
                tenseModel = tenseModel,
                expanded = true,
                onClick = {}
            )
            Spacer(Modifier.height(12.dp))
            LearnTense(
                tenseModel = tenseModel,
                expanded = expanded,
                onClick = {
                    expanded = !expanded
                }
            )
        }
    }
}

@Preview
@Composable
fun Preview_IrregularContainer() {
    HanSanTheme(false) {
        IrregularContainer(
            tenseModel = tenseModel
        )
    }
}

@Preview
@Composable
fun Preview_IrregularItem() {
    HanSanTheme(false) {
        IrregularItem(
            irregularCharacter = "ㅅ",
            irregularDescription = "Drop ㅅ and conjugate from last vowel.\n" +
                    "낫다 - 나았습니다, 짓다 -> 지었습니다"
        )
    }
}

@Preview
@Composable
fun Preview_LearnScreen() {
    val hashMap: HashMap<TenseHeader, List<TenseModel>> = hashMapOf(
        Pair(TenseHeader.PRESENT, tenseModelList),
        Pair(TenseHeader.PAST, tenseModelList),
        Pair(TenseHeader.FUTURE, tenseModelList),
    )
    val state = LearnUiState(
        filterFormalityType = FormalityType.FORMAL_HIGH,
        tenses = tenseModelList,
        tensesShow = hashMap
    )
    HanSanTheme(false) {
        LearnScreen(
            state = state,
            onEvent = {

            }
        )
    }
}

@Preview
@Composable
fun Preview_ExamineWordContainer() {
    Column {

        HanSanTheme(false) {
            Column(
                modifier = Modifier.background(MaterialTheme.colorScheme.background).fillMaxWidth()
            ) {

                ExamineWordContainer(
                    word = "하다",
                    def = "to do"
                )
            }
        }
        HanSanTheme(true) {
            Box(
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            ) {

                ExamineWordContainer(
                    word = "하다",
                    def = "to do"
                )
            }
        }
    }
}
/*
@Preview
@Composable
fun Preview_TenseContainer() {

    Column() {

        HanSanTheme(false) {
            Box(
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            ) {

                TenseContainer(
                    tenseTitle = "Present",
                    tenses = listOf(
                    )
                )
            }
        }
        Spacer(Modifier.height(24.dp))
        HanSanTheme(true) {
            Box(
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            ) {

                TenseContainer(
                    tenseTitle = "Present",
                    tenses = listOf(
                        hadaWordModel.fhPresentDeclarative,
                        hadaWordModel.flPresentDeclarative,
                        hadaWordModel.ilPresentDeclarative
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview_IndividualWordScreen() {
    val state = IndividualWordUiState(
        currentWord = hadaWordModel,
        present = listOf(
            hadaWordModel.fhPresentDeclarative,
            hadaWordModel.flPresentDeclarative,
            hadaWordModel.ilPresentDeclarative
        ),
        past = listOf(
            hadaWordModel.fhPastDeclarative,
            hadaWordModel.flPastDeclarative,
            hadaWordModel.ilPastDeclarative
        ),
        future = listOf(
            hadaWordModel.fhFutureDeclarative,
            hadaWordModel.flFutureDeclarative,
            hadaWordModel.ilFutureDeclarative
        )
    )
    HanSanTheme(false) {
        IndividualWordScreen(
            state = state,
            onNavigate = {}
        )
    }
}*/


@Preview
@Composable
fun Preview_ToggleItem() {
    var enabled by remember { mutableStateOf(false) }
    Column {
        HanSanTheme(false) {
            ToggleItem(
                text = "Daily Reminders",
                isEnabled = enabled,
                onClick = {
                    enabled = it
                }
            )
        }
        Spacer(Modifier.height(24.dp))
        HanSanTheme(true) {
            ToggleItem(
                text = "Daily Reminders",
                isEnabled = enabled,
                onClick = {
                    enabled = it
                }
            )
        }
    }
}

@Preview
@Composable
fun Preview_InputItem() {
    var number by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        HanSanTheme(false) {
            Box(
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)
            ) {

                InputItem(
                    text = "Daily Goal",
                    inputText = number,
                    onValueChange = {
                        number = it
                    }
                )
            }
        }
        Spacer(Modifier.height(24.dp))
        HanSanTheme(true) {
            Box(
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)
            ) {

                InputItem(
                    text = "Daily Goal",
                    inputText = number,
                    onValueChange = {
                        number = it
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview_SettingsScreen() {
    val state = SettingsUiState()
    Column(
    ) {
        HanSanTheme(false) {
            SettingsScreen(
                state = state,
                onEvent = {

                }
            )
        }
    }
}

@Preview
@Composable
fun Preview_IndividualTenseScreen() {
    HanSanTheme(false) {
        IndividualTenseScreen(
            state = tenseModel
        ) {}
    }
}

@Preview
@Composable
private fun Preview_AnswerContainer() {

    var showAnswer by remember { mutableStateOf(false) }
    HanSanTheme(false) {
        Column(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
                .padding(12.dp)
        ) {
            Spacer(Modifier.height(24.dp))
            AnswerCard(
                formalityType = FormalityType.FORMAL_HIGH,
                tenseTarget = tenseModel.tense,
                onClick = {
                    showAnswer = !showAnswer

                },
                showAnswer = showAnswer,
                answer = "하고 있습니다"
            )
            Spacer(Modifier.height(24.dp))
            AnswerCard(
                formalityType = FormalityType.FORMAL_HIGH,
                tenseTarget = tenseModel.tense,
                onClick = {
                          showAnswer = !showAnswer
                },
                showAnswer = !showAnswer,
                answer = "하고 있습니다"
            )
            Spacer(Modifier.height(24.dp))


        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview_AnswerContainerDark() {

    var showAnswer by remember { mutableStateOf(false) }
    HanSanTheme(true) {
        Column(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
                .padding(12.dp)
        ) {
            Spacer(Modifier.height(24.dp))
            AnswerCard(
                formalityType = FormalityType.FORMAL_HIGH,
                tenseTarget = tenseModel.tense,
                onClick = {
                    showAnswer = !showAnswer

                },
                showAnswer = showAnswer,
                answer = "하고 있습니다"
            )
            Spacer(Modifier.height(24.dp))
            AnswerCard(
                formalityType = FormalityType.FORMAL_HIGH,
                tenseTarget = tenseModel.tense,
                onClick = {
                          showAnswer = !showAnswer
                },
                showAnswer = !showAnswer,
                answer = "하고 있습니다"
            )
            Spacer(Modifier.height(24.dp))


        }
    }
}

@Preview
@Composable
private fun Preview_FormalityContainer() {

    HanSanTheme(true) {
        Column(
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)
                .padding(12.dp)
        ) {

            FormalityContainer(
                formalityType = FormalityType.FORMAL_HIGH,
            )

            FormalityContainer(
                formalityType = FormalityType.FORMAL_LOW,
            )

            FormalityContainer(
                formalityType = FormalityType.INFORMAL_LOW,
            )
        }
    }
}