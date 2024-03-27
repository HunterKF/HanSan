package com.jaegerapps.hansan.screens.practice.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.formalityToStringResource
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.getTenseResString
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TargetFormsContainer(
    modifier: Modifier = Modifier,
    tense: TenseModel,
    expanded: Boolean,
    onClick: () -> Unit,
) {

    Column(
        modifier.fillMaxWidth().animateContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.clip(RoundedCornerShape(25.dp))
                .clickable { onClick() },
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.tertiary)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = stringResource(formalityToStringResource(tense.formality)),
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Spacer(Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.tertiary)
                    .padding(horizontal = 12.dp, vertical = 8.dp)

            ) {
                Text(
                    text = stringResource(getTenseResString(tense.tense)),
                    maxLines = 1,
                    overflow = TextOverflow.Visible,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
        AnimatedVisibility(expanded) {
            Column(
                modifier = Modifier.fillMaxWidth(1f).padding(12.dp),
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Conjugation ${tense.conjugation}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = tense.explanation,
                    style = MaterialTheme.typography.labelSmall
                )
            }

        }
    }
}