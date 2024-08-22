package com.jaegerapps.hansan.screens.onboarding.presentation

import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.jaegerapps.hansan.common.notification.KMPNotificationManager
import com.jaegerapps.hansan.common.notification.PermissionChecker
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.screens.onboarding.domain.repo.OnboardingRepo
import com.jaegerapps.hansan.screens.onboarding.presentation.components.OnboardingScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingComponent(
    componentContext: ComponentContext,
    private val onboardingRepo: OnboardingRepo,
    private val kmpNotificationManager: KMPNotificationManager,
    private val permissionChecker: PermissionChecker,
    private val onComplete: () -> Unit,
) : ComponentContext by componentContext {

    private val _state = MutableStateFlow(OnboardingUiState())
    val state = _state.asStateFlow()
    private val _loading = mutableStateOf(false)
    val loading = _loading.value




    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        scope.launch {
            Knower.d("OnboardingComponent", "OnboardingComponent is running through the init block.")
            async { onboardingRepo.insertWords() }.invokeOnCompletion { _loading.value = true }
        }
    }

    fun onNext() {
        when (_state.value.currentScreen) {
            OnboardingScreens.WELCOME_SCREEN -> _state.update { it.copy(currentScreen = OnboardingScreens.REMINDER_SCREEN) }
            OnboardingScreens.REMINDER_SCREEN -> checkNotifications()
            OnboardingScreens.COMPLETE_SCREEN -> {
                onComplete()
            }
        }
    }

    private fun checkNotifications() {
        val isGranted = permissionChecker.checkPostPermission()
        if (!isGranted) {
            permissionChecker.requestPermission()
        }
        kmpNotificationManager.scheduleNotification()

        _state.update { it.copy(currentScreen = OnboardingScreens.COMPLETE_SCREEN) }
    }

    fun onNavigate() {
        onComplete()
    }
}