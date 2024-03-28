package com.jaegerapps.hansan

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.jaegerapps.hansan.common.util.Routes
import com.jaegerapps.hansan.di.AppModule
import com.jaegerapps.hansan.screens.learn.presentation.LearnComponent
import com.jaegerapps.hansan.screens.loading.presentation.LoadingComponent
import com.jaegerapps.hansan.screens.practice.presentation.PracticeComponent
import com.jaegerapps.hansan.screens.settings.presentation.SettingsComponent
import com.jaegerapps.hansan.screens.words.word_individual.IndividualWordComponent
import com.jaegerapps.hansan.screens.words.word_list.presentation.WordsComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.Serializable

class RootComponent(
    componentContext: ComponentContext,
    private val appModule: AppModule,
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    private val state = MutableStateFlow(RootState())


    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.LoadingScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    @OptIn(ExperimentalDecomposeApi::class)
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
                        repo = appModule.practiceRepo,
                        onNavigate = {
                            onNavigate(it)
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

            Configuration.LearnScreen -> {
                Child.LearnScreen(
                    LearnComponent(
                        componentContext = context,
                        tenses = state.value.tenses,
                        onNavigate = {
                            onNavigate(it)
                        }
                    )
                )
            }

            Configuration.WordsScreen -> {
                Child.WordsScreen(
                    WordsComponent(
                        componentContext = context,
                        words = state.value.words,
                        onNavigate = {
                            onNavigate(it)
                        },
                        onWordNavigate = {
                            navigation.pushNew(Configuration.IndividualWordScreen(it))
                        }
                    )
                )
            }

            is Configuration.IndividualWordScreen -> {
                Child.IndividualWordScreen(
                    IndividualWordComponent(
                        currentWord = state.value.words.first { it.dictionaryWord == config.word },
                        componentContext = context,
                        onNavigate = {
                            navigation.pop()
                        }
                    )
                )
            }

            Configuration.SettingsScreen -> {
                Child.SettingsScreen(
                    SettingsComponent(
                        componentContext = context,
                        repo = appModule.settingsRepo,
                        onNavigate = {
                            onNavigate(it)
                        }
                    )
                )
            }
        }
    }

    sealed class Child {
        data class PracticeScreen(val component: PracticeComponent) : Child()
        data class LearnScreen(val component: LearnComponent) : Child()
        data class WordsScreen(val component: WordsComponent) : Child()
        data class LoadingScreen(val component: LoadingComponent) : Child()
        data class IndividualWordScreen(val component: IndividualWordComponent) : Child()
        data class SettingsScreen(val component: SettingsComponent) : Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object PracticeScreen : Configuration()

        @Serializable
        data object LearnScreen : Configuration()

        @Serializable
        data object WordsScreen : Configuration()

        @Serializable
        data object LoadingScreen : Configuration()

        @Serializable
        data class IndividualWordScreen(
            val word: String,
        ) : Configuration()
        @Serializable
        data object SettingsScreen : Configuration()

    }

    private fun onNavigate(route: String) {
        when (route) {
            Routes.LEARN -> navigation.replaceCurrent(Configuration.LearnScreen)
            Routes.PRACTICE -> navigation.replaceAll(Configuration.PracticeScreen)
            Routes.WORDS -> navigation.replaceCurrent(Configuration.WordsScreen)
            Routes.SETTINGS -> navigation.replaceCurrent(Configuration.SettingsScreen)
            else -> navigation.replaceAll(Configuration.PracticeScreen)
        }
    }
}