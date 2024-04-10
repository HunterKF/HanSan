package com.jaegerapps.hansan.screens.learn.presentation.individual_tense.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaegerapps.hansan.common.models.TenseModel

@Composable
fun IrregularContainer(
    modifier: Modifier = Modifier,
    tenseModel: TenseModel,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            "Irregulars",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(12.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            tenseModel.irregularSieut?.let {
                IrregularItem(
                    irregularCharacter = "ㅅ",
                    irregularDescription = it
                )
                tenseModel.irregularDieut?.let {
                    HorizontalDivider()
                }

            }
            tenseModel.irregularDieut?.let {
                IrregularItem(
                    irregularCharacter = "ㄷ",
                    irregularDescription = it
                )
                tenseModel.irregularBieub?.let {
                    HorizontalDivider()
                }

            }

            tenseModel.irregularBieub?.let {
                IrregularItem(
                    irregularCharacter = "ㅂ",
                    irregularDescription = it
                )
                tenseModel.irregularEu?.let {
                    HorizontalDivider()
                }

            }
            tenseModel.irregularEu?.let {
                IrregularItem(
                    irregularCharacter = "ㅡ",
                    irregularDescription = it
                )
                tenseModel.irregularReu?.let {
                    HorizontalDivider()
                }

            }
            tenseModel.irregularReu?.let {
                IrregularItem(
                    irregularCharacter = "르",
                    irregularDescription = it
                )
                tenseModel.irregularRieul?.let {
                    HorizontalDivider()
                }
            }
            tenseModel.irregularRieul?.let {
                IrregularItem(
                    irregularCharacter = "ㄹ",
                    irregularDescription = it
                )

            }
        }

    }
}