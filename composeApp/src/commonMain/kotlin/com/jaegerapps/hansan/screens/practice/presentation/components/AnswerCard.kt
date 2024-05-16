package com.jaegerapps.hansan.screens.practice.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.ThumbsUpDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.getResStringFromFormality
import com.jaegerapps.hansan.common.models.getTenseFromString
import com.jaegerapps.hansan.common.models.getTenseResString
import com.jaegerapps.hansan.screens.practice.presentation.PracticeUiEvent
import com.jaegerapps.hansan.ui.theme.blue
import com.jaegerapps.hansan.ui.theme.orange
import com.jaegerapps.hansan.ui.theme.teal
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AnswerCard(
    modifier: Modifier = Modifier,
    formality: Formality,
    answer: String,
    tenseTarget: Tense,
    showAnswer: Boolean,
    onClick: (PracticeUiEvent) -> Unit,
) {

    Column(
        modifier = modifier,
    ) {
        FormalityContainer(
            modifier = Modifier
                .zIndex(2f)
                .align(Alignment.CenterHorizontally)
                .offset(y = 32.dp),
            formality = formality
        )
        Column(
            modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(25.dp))
                .shadow(5.dp)
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(12.dp))
            Box(
                modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min).padding(36.dp),
                contentAlignment = Alignment.Center
            ) {
                if (!showAnswer) {
                    Text(
                        text = stringResource(getTenseResString(tenseTarget)),
                        style = MaterialTheme.typography.bodyLarge
                    )
                } else {
                    Text(
                        text = answer,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

            }
            Box(
                modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min).padding(16.dp),
                contentAlignment = Alignment.Center
            ) {

                if (showAnswer) {
                    Row(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(
                                onClick = {
                                    onClick(PracticeUiEvent.ClickDon_tKnow)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ThumbDown,
                                    contentDescription = null
                                )
                            }
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(
                                onClick = {
                                    onClick(PracticeUiEvent.ClickGotIt)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ThumbUp,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                } else {
                    TextButton(
                        modifier = Modifier.fillMaxHeight().padding(1.dp),
                        onClick = {
                            onClick(PracticeUiEvent.CheckAnswer)
                        }
                    ) {
                        Text(
                            text = "Tap for answer",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun FormalityContainer(modifier: Modifier = Modifier, formality: Formality) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .border(8.dp, shape = RoundedCornerShape(50.dp), color = MaterialTheme.colorScheme.surfaceContainer)
            .padding(8.dp)
            .background(getFormalityColor(formality = formality))
            .padding(horizontal = 34.dp, vertical = 8.dp)
    ) {
        Text(
            text = stringResource(getResStringFromFormality(formality)),
            color = Color.White
        )
    }
}

private fun getFormalityColor(formality: Formality): Color {
    return when (formality) {
        Formality.FORMAL_HIGH -> orange
        Formality.FORMAL_LOW -> teal
        Formality.INFORMAL_LOW -> blue
    }
}