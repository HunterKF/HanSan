package com.jaegerapps.hansan.screens.onboarding.presentation

import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.screens.onboarding.domain.repo.OnboardingRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class OnboardingComponent(
    componentContext: ComponentContext,
    private val onboardingRepo: OnboardingRepo,
    private val onComplete: () -> Unit,
) : ComponentContext by componentContext {

    private val _loading = mutableStateOf(false)
    val loading = _loading.value

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        scope.launch {
            Knower.d("OnboardingComponent", "OnboardingComponent is running through the init block.")
            async { onboardingRepo.insertWords() }.invokeOnCompletion { _loading.value = true }
        }
    }

    fun onNavigate() {
        onComplete()
    }
}