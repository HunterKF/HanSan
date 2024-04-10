package com.jaegerapps.hansan.screens.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.jaegerapps.hansan.common.components.BottomBarIcon
import com.jaegerapps.hansan.common.util.BottomBarRouteIcon
import com.jaegerapps.hansan.common.util.Routes
import com.jaegerapps.hansan.screens.practice.presentation.PracticeErrorMessage
import com.jaegerapps.hansan.screens.practice.presentation.PracticeUiEvent
import com.jaegerapps.hansan.screens.settings.presentation.components.InputItem
import com.jaegerapps.hansan.screens.settings.presentation.components.ToggleItem
import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources.error_answer_blank
import hansan.composeapp.generated.resources.error_daily_goal_blank
import hansan.composeapp.generated.resources.error_not_korean
import hansan.composeapp.generated.resources.error_tense_blank
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getString

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SettingsScreen(
    state: SettingsUiState,
    onEvent: (SettingsUiEvent) -> Unit,
    padding: PaddingValues = PaddingValues(12.dp),
) {

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var message: String? by remember { mutableStateOf(null) }
    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let {
            if (message == null) {
                scope.launch {
                    message = when (it) {
                        SettingsErrorMessage.TENSE_BLANK -> getString(Res.string.error_tense_blank)
                        SettingsErrorMessage.DAILY_BLANK -> getString(Res.string.error_daily_goal_blank)
                    }
                    snackbarHostState.showSnackbar(message!!)
                    onEvent(SettingsUiEvent.ClearErrorMessage)
                    message = null
                }
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.background
            ) {
                BottomBarRouteIcon.routeList.forEach {
                    BottomBarIcon(
                        modifier = Modifier.weight(1f),
                        icon = it.icon,
                        text = it.route,
                        selected = it.route == Routes.SETTINGS,
                        onClick = {
                            onEvent(SettingsUiEvent.OnNavigate(it.route))
                        }
                    )
                }
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxWidth().padding(paddingValues)
        ) {
            ItemHeader(padding, "General")

            ToggleItem(
                text = "Daily Reminder",
                isEnabled = state.enableReminders,
                onClick = {
                    onEvent(SettingsUiEvent.ToggleDailyReminders(it))
                }
            )
            InputItem(
                text = "Daily Goal",
                inputText = state.dailyTarget.toString(),
                onValueChange = {
                    onEvent(SettingsUiEvent.ChangeDailyTarget(it))
                }
            )
            ItemHeader(padding, "Practice")
            ToggleItem(
                text = "Present Tense",
                isEnabled = state.presentTenseEnabled,
                onClick = {
                    onEvent(SettingsUiEvent.TogglePresentTense(it))
                }
            )
            ToggleItem(
                text = "Past Tense",
                isEnabled = state.pastTenseEnabled,
                onClick = {
                    onEvent(SettingsUiEvent.TogglePastTense(it))
                }
            )
            ToggleItem(
                text = "Future Tense",
                isEnabled = state.futureTenseEnabled,
                onClick = {
                    onEvent(SettingsUiEvent.ToggleFutureTense(it))
                }
            )

        }
    }
}

@Composable
private fun ItemHeader(
    padding: PaddingValues,
    text: String
) {
    Box(
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.tertiary)
            .padding(padding),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text
        )
    }
}