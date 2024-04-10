package com.jaegerapps.hansan.screens.learn.presentation.tense_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaegerapps.hansan.common.components.BottomBarIcon
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.getResStringFromFormality
import com.jaegerapps.hansan.common.models.getTenseResString
import com.jaegerapps.hansan.common.util.BottomBarRouteIcon
import com.jaegerapps.hansan.common.util.Routes
import com.jaegerapps.hansan.screens.learn.presentation.components.FormSelectorItem
import com.jaegerapps.hansan.screens.learn.presentation.components.LearnTense
import com.jaegerapps.hansan.screens.learn.presentation.components.getStringFromHeader
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LearnScreen(
    state: LearnUiState,
    onEvent: (LearnUiEvent) -> Unit,
    padding: PaddingValues = PaddingValues(12.dp),

    ) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.background
            ) {
                BottomBarRouteIcon.routeList.forEach {
                    BottomBarIcon(
                        modifier = Modifier.weight(1f),
                        icon = it.icon,
                        text = it.route,
                        selected = it.route == Routes.LEARN,
                        onClick = {
                            onEvent(LearnUiEvent.OnNavigate(it.route))
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxWidth().padding(paddingValues).padding(top = 12.dp)
        ) {
            val list = listOf(
                Formality.FORMAL_HIGH,
                Formality.FORMAL_LOW,
                Formality.INFORMAL_LOW
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                list.forEach {
                    FormSelectorItem(
                        modifier = Modifier.weight(1.0f),
                        text = stringResource(getResStringFromFormality(it)),
                        selected = it == state.filterFormality,
                        onSelect = {
                            onEvent(LearnUiEvent.ChangeFormality(it))
                        }
                    )
                }
            }
            var expanded: Tense? by remember { mutableStateOf(null) }
            Spacer(Modifier.height(12.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.tensesShow.keys.toList()) { tense ->
                    //
                    Column {
                        TenseHeader(
                            padding = padding,
                            text = stringResource(getStringFromHeader(tense))
                        )
                        state.tensesShow[tense]?.forEach {
                            LearnTense(
                                tenseModel = it,
                                expanded = it.tense == expanded,
                                onClick = {
                                    onEvent(LearnUiEvent.OnNavigateToTense(tense = it.tense.name))
                                }
                            )
                            /*if (state.tensesShow.lastIndex != index) {
                                HorizontalDivider(Modifier.fillMaxWidth())
                            }*/
                        }

                    }


                }
            }
        }

    }
}

@Composable
private fun TenseHeader(
    padding: PaddingValues,
    text: String,
) {
    Box(
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.tertiary)
            .padding(padding),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
    }
}