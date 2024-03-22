package com.jaegerapps.hansan

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.essenty.lifecycle.doOnCreate
import com.jaegerapps.hansan.di.AppModule
import com.jaegerapps.hansan.presentation.loading.presentation.LoadingComponent
import com.jaegerapps.hansan.presentation.practice.presentation.PracticeComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class RootComponent(
    componentContext: ComponentContext,
    private val appModule: AppModule
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    private val state = MutableStateFlow(RootState())

    private val scope = CoroutineScope(Dispatchers.IO)

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.LoadingScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: Configuration,
        context: ComponentContext,
    ): Child {
        return when (config) {
            Configuration.PracticeScreen -> {
                Child.PracticeScreen(
                    PracticeComponent(
                        componentContext = context,
                        tenses = state.value.tenses,
                        words = state.value.words,
                        onNavigate = {

                        }
                    )
                )
            }

            Configuration.LoadingScreen -> {
                Child.LoadingScreen(
                    LoadingComponent(
                        componentContext = context,
                        repo = appModule.loadingRepo,
                        onStart = { words, tenses ->
                            state.update {
                                it.copy(
                                    words = words,
                                    tenses = tenses,
                                )
                            }
                            navigation.replaceAll(Configuration.PracticeScreen)
                        }
                    )
                )
            }
        }
    }

    sealed class Child {
        data class PracticeScreen(val component: PracticeComponent) : Child()
        data class LoadingScreen(val component: LoadingComponent) : Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object PracticeScreen : Configuration()
        @Serializable
        data object LoadingScreen : Configuration()

    }
}