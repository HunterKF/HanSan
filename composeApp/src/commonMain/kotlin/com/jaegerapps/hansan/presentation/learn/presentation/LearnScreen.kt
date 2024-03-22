package com.jaegerapps.hansan.presentation.learn.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaegerapps.hansan.common.components.BottomBarIcon
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.formalityToStringResource
import com.jaegerapps.hansan.common.util.BottomBarRouteIcon
import com.jaegerapps.hansan.common.util.Routes
import com.jaegerapps.hansan.presentation.learn.presentation.components.FormSelectorItem
import com.jaegerapps.hansan.presentation.practice.presentation.PracticeUiEvent
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
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(state.)
            }
        }

    }
}