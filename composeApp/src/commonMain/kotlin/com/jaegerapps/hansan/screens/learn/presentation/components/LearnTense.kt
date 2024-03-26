package com.jaegerapps.hansan.screens.learn.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.getTenseResString
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LearnTense(
    modifier: Modifier = Modifier,
    tenseModel: TenseModel,
    expanded: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth().clickable { onClick() },
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = stringResource(getTenseResString(tenseModel.tense)),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = tenseModel.conjugation,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        AnimatedVisibility(expanded) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = tenseModel.explanation,
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = tenseModel.exampleGada,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = tenseModel.exampleBoda,
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = tenseModel.exampleMokda,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = tenseModel.exampleHada,
                        style = MaterialTheme.typography.labelMedium
                    )
                }


            }
        }
    }
}