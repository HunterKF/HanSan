package com.jaegerapps.hansan.presentation.practice.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import com.jaegerapps.hansan.presentation.practice.presentation.PracticeUiEvent


@Composable
fun KeyboardInputContainer(
    modifier: Modifier = Modifier,
    input: String,
    onEvent: (PracticeUiEvent) -> Unit,
) {
    /*Container for the keyboard
    * Only used on the practice screen*/
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = input,
            textStyle = MaterialTheme.typography.labelMedium,
            onValueChange = {
                onEvent(PracticeUiEvent.OnValueChange(it))
            },
            placeholder = {
                Text(
                    "Tap here to type",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            },
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                cursorColor = MaterialTheme.colorScheme.tertiary,
                selectionColors = TextSelectionColors(
                    handleColor = MaterialTheme.colorScheme.onBackground,
                    backgroundColor = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = 0.2f
                    )
                )
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onEvent(PracticeUiEvent.EnterAnswerKeyboard)
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            )
        )

    }

}
