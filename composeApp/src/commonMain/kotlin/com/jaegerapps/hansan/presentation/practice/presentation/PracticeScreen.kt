package com.jaegerapps.hansan.presentation.practice.presentation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import com.jaegerapps.hansan.common.components.BottomBarIcon
import com.jaegerapps.hansan.common.models.formalityToStringResource
import com.jaegerapps.hansan.common.models.typeToStringResource
import com.jaegerapps.hansan.common.util.BottomBarRouteIcon.Companion.routeList
import com.jaegerapps.hansan.common.util.Routes
import com.jaegerapps.hansan.presentation.practice.presentation.components.CurrentTenseContainer
import com.jaegerapps.hansan.presentation.practice.presentation.components.DropDownContainer
import com.jaegerapps.hansan.presentation.practice.presentation.components.KeyboardEnabledIcon
import com.jaegerapps.hansan.presentation.practice.presentation.components.KeyboardInputContainer
import com.jaegerapps.hansan.presentation.practice.presentation.components.WordContainer
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PracticeScreen(state: PracticeUiState, onEvent: (PracticeUiEvent) -> Unit) {
    Scaffold(
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
        }
    ) { paddingValues ->
        val blur by animateDpAsState(
            if (state.typeDropDown || state.formalityDropDown) 10.dp else 0.dp,
            tween(300)
        )
        Column(
            modifier = Modifier.padding(paddingValues).padding(horizontal = 12.dp),
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
                        onClick = { onEvent(PracticeUiEvent.ToggleKeyboard) }
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