package com.jaegerapps.hansan

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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowUp
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
import com.jaegerapps.hansan.common.components.getScreenSizeInfo
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.ModifierType
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.WordModel
import com.jaegerapps.hansan.common.models.WordTenseModel
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
import com.jaegerapps.hansan.screens.learn.presentation.individual_tense.IndividualTenseUiState
import com.jaegerapps.hansan.screens.practice.domain.models.AnswerResponse
import com.jaegerapps.hansan.screens.practice.presentation.PracticeErrorMessage
import com.jaegerapps.hansan.screens.practice.presentation.PracticeScreen
import com.jaegerapps.hansan.screens.practice.presentation.PracticeUiState
import com.jaegerapps.hansan.screens.practice.presentation.components.AnswerItem
import com.jaegerapps.hansan.screens.practice.presentation.components.TargetFormsContainer
import com.jaegerapps.hansan.screens.practice.presentation.components.DropDownContainer
import com.jaegerapps.hansan.screens.practice.presentation.components.ErrorBox
import com.jaegerapps.hansan.screens.practice.presentation.components.KeyboardContainer
import com.jaegerapps.hansan.screens.practice.presentation.components.KeyboardEnabledIcon
import com.jaegerapps.hansan.screens.practice.presentation.components.KeyboardIconButton
import com.jaegerapps.hansan.screens.practice.presentation.components.KeyboardInputContainer
import com.jaegerapps.hansan.screens.practice.presentation.components.KeyboardKey
import com.jaegerapps.hansan.screens.practice.presentation.components.WordContainer
import com.jaegerapps.hansan.screens.settings.presentation.SettingsScreen
import com.jaegerapps.hansan.screens.settings.presentation.SettingsUiState
import com.jaegerapps.hansan.screens.settings.presentation.components.InputItem
import com.jaegerapps.hansan.screens.settings.presentation.components.ToggleItem
import com.jaegerapps.hansan.screens.words.word_individual.IndividualWordScreen
import com.jaegerapps.hansan.screens.words.word_individual.IndividualWordUiState
import com.jaegerapps.hansan.screens.words.word_individual.component.ExamineWordContainer
import com.jaegerapps.hansan.screens.words.word_individual.component.TenseContainer
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource


private val hadaWordModel = WordModel(
    dictionaryWord = "하다",
    definition = "to do",
    type = ModifierType.VERBS,
    irregular = false,
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
private val tenseModel = TenseModel(
    tense = Tense.PAST_DECLARATIVE,
    formality = Formality.FORMAL_HIGH,
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
        formality = Formality.FORMAL_HIGH,
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
        formality = Formality.FORMAL_HIGH,
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
        formality = Formality.FORMAL_HIGH,
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
            TargetFormsContainer(
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
                    formality = Formality.FORMAL_LOW,
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
fun Preview_PracticeScreenErrorMessage() {
    var errorMessage: PracticeErrorMessage? by remember { mutableStateOf(null) }
    val state = PracticeUiState(
        currentWord = hadaWordModel,
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
                text = stringResource(getResStringFromFormality(Formality.FORMAL_HIGH)),
                selected = false,
                onSelect = {

                }
            )
            FormSelectorItem(
                text = stringResource(getResStringFromFormality(Formality.FORMAL_HIGH)),
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
        filterFormality = Formality.FORMAL_HIGH,
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
                        hadaWordModel.fhPresentDeclarative,
                        hadaWordModel.flPresentDeclarative,
                        hadaWordModel.ilPresentDeclarative
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
}

@Preview
@Composable
fun Preview_KeyboardKey() {
    val size = getScreenSizeInfo().wDP
    val width = (size - (4.dp * 10)) / 10
    Column {
        HanSanTheme(false) {
            Row() {
                KeyboardKey(
                    key = "ㅎ",
                    isShift = false,
                    onClick = {}
                )
                Spacer(Modifier.width(12.dp))
                KeyboardKey(
                    key = "ㄲ",
                    isShift = false,
                    onClick = {}
                )
                Spacer(Modifier.width(12.dp))
                KeyboardKey(
                    width = size / 10,
                    key = "ㄲ",
                    isShift = false,
                    onClick = {}
                )
            }
            Text("Width: $width")
            Text("Size: ${size}")
            Row {
                for (i in 1..10) {
                    KeyboardKey(
                        width = width,
                        key = "$i",
                        isShift = false,
                        onClick = {}
                    )
                }
            }
            KeyboardKey(
                key = "ㅎ",
                isShift = false,
                onClick = {}
            )
            Spacer(Modifier.height(12.dp))
            KeyboardKey(
                key = "ㄲ",
                isShift = false,
                onClick = {}
            )
        }
        HanSanTheme(true) {
            Spacer(Modifier.height(12.dp))

            KeyboardKey(
                key = "ㅎ",
                isShift = false,
                onClick = {}
            )
        }
    }

}

@Preview
@Composable
fun Preview_KeyboardContainer() {
    Column {
        HanSanTheme(false) {
            KeyboardContainer(
                input = "",
                onEvent = {}
            )
        }
        Spacer(Modifier.height(12.dp))
        HanSanTheme(true) {
            KeyboardContainer(
                input = "",
                onEvent = {}
            )
        }
    }
}

@Preview
@Composable
fun Preview_KeyboardIconButton() {
    Column {
        HanSanTheme(false) {
            KeyboardIconButton(
                icon = Icons.Sharp.KeyboardArrowUp,
                contentDescription = "shift",
                onClick = {}
            )
        }
        Spacer(Modifier.height(12.dp))
        HanSanTheme(true) {
            KeyboardIconButton(
                icon = Icons.Sharp.KeyboardArrowUp,
                contentDescription = "shift",
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
fun Preview_ErrorBox() {
    Column {

        HanSanTheme(false) {
            ErrorBox(message = "Please use Korean.")
        }
        Spacer(Modifier.height(25.dp))
        HanSanTheme(true) {
            ErrorBox(message = "Please use Korean.")
        }
    }
}

@Preview
@Composable
fun Preview_AnswerContainer() {
    Column {

        HanSanTheme(false) {
            AnswerItem(answer = "했어") {

            }
        }
        Spacer(Modifier.height(25.dp))
        HanSanTheme(true) {
            Box(
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)
            ) {

                AnswerItem(answer = "했어") {}
            }
        }
        Spacer(Modifier.height(25.dp))

        HanSanTheme(false) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                AnswerItem(answer = "했어") {

                }

                AnswerItem(answer = "했어요") {

                }
            }
            Spacer(Modifier.height(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                AnswerItem(answer = "걱정스러웠어요") {

                }

                AnswerItem(answer = "걱정스러웠겠네요") {

                }
            }
        }
    }
}

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