package com.jaegerapps.hansan.screens.practice.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material.icons.automirrored.sharp.ExitToApp
import androidx.compose.material.icons.automirrored.sharp.Send
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.jaegerapps.hansan.common.components.getScreenSizeInfo
import com.jaegerapps.hansan.screens.practice.presentation.PracticeUiEvent
import com.jaegerapps.hansan.screens.practice.presentation.components.uimodel.CharacterKeyModel.Companion.bottomRow
import com.jaegerapps.hansan.screens.practice.presentation.components.uimodel.CharacterKeyModel.Companion.middleRow
import com.jaegerapps.hansan.screens.practice.presentation.components.uimodel.CharacterKeyModel.Companion.topRow

@Composable
fun KeyboardContainer(
    modifier: Modifier = Modifier,
    input: String,
    onEvent: (PracticeUiEvent) -> Unit,
) {

    val size = getScreenSizeInfo().wDP
    val width = (size - (6.dp * 10)) / 10
    var isShift by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxWidth().background(MaterialTheme.colorScheme.tertiary)
    ) {
        Spacer(Modifier.height(12.dp))

        /*Top row*/
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            topRow.forEach {
                KeyboardKey(
                    it.key,
                    shiftKey = it.shiftKey,
                    width = width,
                    isShift = isShift,
                    onClick = {
                        val lastTextValue = input
                        val inputKey = if (isShift) it.shiftKey else it.key
                        inputKey?.let {
                            onEvent(PracticeUiEvent.OnValueChange(lastTextValue.plus(AnnotatedString(text = it))))

                        }

                    })
                if (it != topRow.last()) {
                    Spacer(Modifier.width(4.dp))
                }
            }
        }
        Spacer(Modifier.height(6.dp))
        /*Middle row*/
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            middleRow.forEach {
                KeyboardKey(
                    it.key,
                    shiftKey = it.shiftKey,
                    width = width,
                    isShift = isShift,
                    onClick = {
                        val lastTextValue = input
                        val inputKey = if (isShift) it.shiftKey else it.key
                        inputKey?.let {
                            onEvent(PracticeUiEvent.OnValueChange(lastTextValue.plus(AnnotatedString(text = it))))

                        }

                    })
                if (it != middleRow.last()) {
                    Spacer(Modifier.width(4.dp))
                }
            }
        }
        Spacer(Modifier.height(6.dp))

        /*Bottom row, shift  and backspace*/

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp).height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            //shift key
            KeyboardIconButton(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                icon = Icons.Sharp.KeyboardArrowUp,
                contentDescription = "",
                onClick = {
                    isShift = !isShift
                }
            )
            Spacer(Modifier.width(4.dp))

            bottomRow.forEach {
                KeyboardKey(
                    it.key,
                    shiftKey = it.shiftKey,
                    width = width,
                    isShift = isShift,
                    onClick = {
                        val lastTextValue = input
                        val inputKey = if (isShift) it.shiftKey else it.key
                        inputKey?.let {
                            onEvent(PracticeUiEvent.OnValueChange(lastTextValue.plus(AnnotatedString(text = it))))

                        }
                    })
                if (it != bottomRow.last()) {
                    Spacer(Modifier.width(4.dp))
                }
            }
            Spacer(Modifier.width(4.dp))
            //backspace key

            KeyboardIconButton(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                icon = Icons.AutoMirrored.Sharp.ArrowBack,
                contentDescription = "",
                onClick = {
                    onEvent(PracticeUiEvent.OnValueChange(input.dropLast(1)))
                }
            )
        }
        Spacer(Modifier.height(6.dp))
        /*
        Space and enter row*/
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp).height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            KeyboardIconButton(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                icon = Icons.AutoMirrored.Sharp.ExitToApp,
                contentDescription = "",
                onClick = {
                    onEvent(PracticeUiEvent.OpenKeyboard)

                }
            )
            Box(
                Modifier.width(width)
                    .heightIn(max = 48.dp)
                    .aspectRatio(0.8f)
            )
            Spacer(
                modifier = Modifier.weight(0.5f),
            )

            Spacer(Modifier.width(12.dp))

            KeyboardIconButton(
                modifier = Modifier.weight(3f).fillMaxHeight(),
                icon = Icons.AutoMirrored.Sharp.ArrowBack,
                text = "space",
                contentDescription = "",
                onClick = {
                    onEvent(PracticeUiEvent.OnValueChange("$input "))
                }
            )
            Spacer(Modifier.width(12.dp))

            KeyboardIconButton(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                icon = Icons.AutoMirrored.Sharp.Send,
                contentDescription = "",
                onClick = {

                }
            )
        }
        Spacer(Modifier.height(12.dp))


    }
}