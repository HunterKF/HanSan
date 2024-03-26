package com.jaegerapps.hansan.screens.practice.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.WindowInfo
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.jaegerapps.hansan.common.components.BottomBarIcon
import com.jaegerapps.hansan.common.models.formalityToStringResource
import com.jaegerapps.hansan.common.models.typeToStringResource
import com.jaegerapps.hansan.common.util.BottomBarRouteIcon.Companion.routeList
import com.jaegerapps.hansan.common.util.Routes
import com.jaegerapps.hansan.screens.practice.presentation.components.CurrentTenseContainer
import com.jaegerapps.hansan.screens.practice.presentation.components.DropDownContainer
import com.jaegerapps.hansan.screens.practice.presentation.components.KeyboardContainer
import com.jaegerapps.hansan.screens.practice.presentation.components.KeyboardEnabledIcon
import com.jaegerapps.hansan.screens.practice.presentation.components.KeyboardInputContainer
import com.jaegerapps.hansan.screens.practice.presentation.components.WordContainer
import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources.error_answer_blank
import hansan.composeapp.generated.resources.error_not_korean
import hansan.composeapp.generated.resources.prompt_type_here
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PracticeScreen(state: PracticeUiState, onEvent: (PracticeUiEvent) -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }
    var message: String? by remember { mutableStateOf(null) }
    val scope = rememberCoroutineScope()
    var showErrorMessage by remember { mutableStateOf(false) }
    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let {
            if (message == null) {
                scope.launch {
                    message = when (it) {
                        PracticeErrorMessage.NOT_KOREAN -> getString(Res.string.error_not_korean)
                        PracticeErrorMessage.ANSWER_BLANK -> getString(Res.string.error_answer_blank)
                    }
                    showErrorMessage = true
                    onEvent(PracticeUiEvent.ClearErrorMessage)
                    delay(1000)
                    showErrorMessage = false

                    message = null
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.background
            ) {
                routeList.forEach {
                    BottomBarIcon(
                        modifier = Modifier.weight(1f),
                        icon = it.icon,
                        text = it.route,
                        selected = it.route == Routes.PRACTICE,
                        onClick = {
                            onEvent(PracticeUiEvent.OnNavigate(it.route))
                        }
                    )
                }
            }
        },
    ) { paddingValues ->
        val blur by animateDpAsState(
            if (state.typeDropDown || state.formalityDropDown) 10.dp else 0.dp,
            tween(300)
        )
        AnimatedVisibility(
            showErrorMessage,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier.padding(12.dp).fillMaxWidth().zIndex(3f)
                    .background(MaterialTheme.colorScheme.tertiary).padding(12.dp)
            ) {
                Text(
                    text = message ?: "Unknown error",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
        Column(
            modifier = Modifier.padding(paddingValues).padding(horizontal = 12.dp).fillMaxSize()
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DropDownContainer(
                    modifier = Modifier.blur(if (!state.typeDropDown && state.formalityDropDown) 10.dp else 0.dp),
                    selected = stringResource(typeToStringResource(state.targetType)),
                    expanded = state.typeDropDown,
                    list = state.typeList.map { stringResource(typeToStringResource(it)) },
                    onExpand = {
                        onEvent(PracticeUiEvent.ToggleTypeDropDown)
                    },
                    onSelect = {
                        onEvent(PracticeUiEvent.SelectType(it))
                    }
                )
                DropDownContainer(
                    modifier = Modifier.blur(if (state.typeDropDown && !state.formalityDropDown) 10.dp else 0.dp),
                    selected = stringResource(formalityToStringResource(state.targetFormality)),
                    expanded = state.formalityDropDown,
                    list = state.formalityList.map { stringResource(formalityToStringResource(it)) },
                    onExpand = {
                        onEvent(PracticeUiEvent.ToggleFormalityDropDown)
                    },
                    onSelect = {
                        onEvent(PracticeUiEvent.SelectType(it))
                    }
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth().blur(blur)
            ) {
                WordContainer(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    word = state.currentWord?.dictionaryWord ?: "고장",
                    definition = state.currentWord?.definition ?: "error",
                    answerResponse = state.answerResponse,
                    onEvent = {
                        onEvent(PracticeUiEvent.ClearAnswer)
                    }
                )
                state.targetTense?.let {
                    CurrentTenseContainer(
                        tense = it,
                        expanded = state.tenseExplanationExpanded,
                        onClick = {
                            onEvent(PracticeUiEvent.ToggleTenseExplanation)
                        }
                    )
                }
                Box(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ) {
                    KeyboardEnabledIcon(
                        modifier = Modifier.align(Alignment.TopEnd),
                        enabled = state.keyboardEnabled,
                        onClick = { onEvent(PracticeUiEvent.ToggleKeyboardMode) }
                    )
                    KeyboardInputContainer(
                        modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
                        input = state.textInput,
                        onEvent = {
                            onEvent(it)
                        }
                    )
                }
            }

        }

    }

}