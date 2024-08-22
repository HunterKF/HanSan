package com.jaegerapps.hansan.screens.practice.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.jaegerapps.hansan.common.components.BottomBarIcon
import com.jaegerapps.hansan.common.util.BottomBarRouteIcon.Companion.routeList
import com.jaegerapps.hansan.common.util.Routes
import com.jaegerapps.hansan.screens.practice.presentation.components.AnswerCard
import com.jaegerapps.hansan.screens.practice.presentation.components.WordContainer
import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources.error_answer_blank
import hansan.composeapp.generated.resources.error_not_korean
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getString

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
                    delay(1500)
                    showErrorMessage = false
                    //Give time for the animation to fade
                    delay(200)
                    message = null
                }
            }
        }
    }



    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
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

        Column(
            modifier = Modifier.padding(paddingValues).padding(horizontal = 12.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(Modifier.height(16.dp))
                DailyGoalsContainer(state)
                //This box contains the word used for practice. Aka, the base word and the def above
                Box(
                    modifier = Modifier.fillMaxWidth().weight(0.7f),
                    contentAlignment = Alignment.Center
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator()
                    } else {

                        WordContainer(
                            modifier = Modifier.fillMaxWidth(),
                            word = state.targetWord?.baseWord ?: "고장",
                            definition = state.targetWord?.translations?.firstOrNull()?.translation
                                ?: "error",
                        )
                    }
                }
                //Displays the practice card.
                //Formality is above, in the middle is the tense, bottom is a prompt to display the answer.
                Column(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ) {
                    state.targetTense?.let { tenseModel ->
                        state.targetFormalityType?.let { formality ->
                            AnswerCard(
                                formalityType = formality,
                                tenseTarget = tenseModel,
                                showAnswer = state.showAnswer,
                                answer = state.targetWord?.conjugatedWord ?: "Error",
                                onClick = {
                                    onEvent(it)
                                }
                            )
                        }
                    }
                }
                //This will eventually be used to click and display the word and all of the bases for it.
                //Basically a pop up with the words by tenses.
                Box(
                    modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer)
                        .clip(RoundedCornerShape(25.dp))
                        .padding(12.dp).clickable {

                        }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = null
                    )
                }
            }

        }

    }

}

@Composable
private fun DailyGoalsContainer(state: PracticeUiState) {
    val progress by animateFloatAsState(
        targetValue = (state.goal?.current ?: (0 + 1)) / (state.goal?.target?.toFloat() ?: 1f)
    )
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.secondary.copy(0.5f),
            progress = { progress },
            strokeCap = StrokeCap.Round
        )
        Spacer(Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Daily Goal",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "${state.goal?.current} / ${state.goal?.target}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

