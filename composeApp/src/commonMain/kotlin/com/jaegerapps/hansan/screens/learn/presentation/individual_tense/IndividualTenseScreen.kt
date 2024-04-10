package com.jaegerapps.hansan.screens.learn.presentation.individual_tense

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.getResStringFromFormality
import com.jaegerapps.hansan.common.models.getTenseResString
import com.jaegerapps.hansan.screens.learn.presentation.individual_tense.components.IrregularContainer
import com.jaegerapps.hansan.screens.learn.presentation.components.checkForIrregulars
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun IndividualTenseScreen(
    state: TenseModel,
    onEvent: (IndividualTenseUiEvent) -> Unit,
) {
    val scrollState = rememberScrollState()
    Scaffold { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.background(MaterialTheme.colorScheme.tertiary)
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(getTenseResString(state.tense)),
                        style = MaterialTheme.typography.displayMedium.copy(
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    )
                    IconButton(
                        onClick = {
                            onEvent(IndividualTenseUiEvent.OnNavigateBack)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Sharp.ArrowBack,
                            contentDescription = "back",
                            tint = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(getResStringFromFormality(state.formality)),
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Base conjugation",
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    )
                    Text(
                        text = state.conjugation,
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    )
                }
            }


            Column(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = state.explanation,
                    style = MaterialTheme.typography.labelMedium.copy(
                        lineBreak = LineBreak.Paragraph,
                        lineHeight = 24.sp
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = state.exampleGada,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = state.exampleBoda,
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = state.exampleMokda,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = state.exampleHada,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                if (checkForIrregulars(state)) {
                    IrregularContainer(
                        tenseModel = state
                    )
                }


            }
        }
    }
}