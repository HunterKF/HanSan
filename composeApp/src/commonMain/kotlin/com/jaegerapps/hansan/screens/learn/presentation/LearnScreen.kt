package com.jaegerapps.hansan.screens.learn.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaegerapps.hansan.common.components.BottomBarIcon
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.formalityToStringResource
import com.jaegerapps.hansan.common.util.BottomBarRouteIcon
import com.jaegerapps.hansan.common.util.Routes
import com.jaegerapps.hansan.screens.learn.presentation.components.FormSelectorItem
import com.jaegerapps.hansan.screens.learn.presentation.components.LearnTense
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LearnScreen(
    state: LearnUiState,
    onEvent: (LearnUiEvent) -> Unit,
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
        Column(modifier = Modifier.fillMaxWidth().padding(paddingValues).padding(12.dp)) {
            val list = listOf(
                Formality.FORMAL_HIGH,
                Formality.FORMAL_LOW,
                Formality.INFORMAL_LOW
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                list.forEach {
                    val weight by animateFloatAsState(
                        targetValue = if (it == state.filterFormality) 1f else 0.8f,
                        tween()
                    )
                    FormSelectorItem(
                        modifier = Modifier.weight(weight),
                        text = stringResource(formalityToStringResource(it)),
                        selected = it == state.filterFormality,
                        onSelect = {
                            onEvent(LearnUiEvent.ChangeFormality(it))
                        }
                    )
                }
            }
            var expanded by remember { mutableStateOf(-1) }
            Spacer(Modifier.height(12.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(state.tensesShow) { index, tense ->
                    Column {
                        LearnTense(
                            tenseModel = tense,
                            expanded = index == expanded,
                            onClick = {
                                expanded = if (expanded == index) -1 else index
                            }
                        )
                        if (state.tensesShow.lastIndex != index) {
                            HorizontalDivider(Modifier.fillMaxWidth())
                        }
                    }

                }
            }
        }

    }
}