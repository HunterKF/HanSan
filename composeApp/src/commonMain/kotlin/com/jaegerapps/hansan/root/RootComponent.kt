package com.jaegerapps.hansan.root

import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.common.util.Routes
import com.jaegerapps.hansan.di.AppModule
import com.jaegerapps.hansan.screens.learn.presentation.individual_tense.IndividualTenseComponent
import com.jaegerapps.hansan.screens.learn.presentation.tense_list.TensesComponent
import com.jaegerapps.hansan.screens.loading.presentation.LoadingComponent
import com.jaegerapps.hansan.screens.onboarding.presentation.OnboardingComponent
import com.jaegerapps.hansan.screens.practice.presentation.PracticeComponent
import com.jaegerapps.hansan.screens.settings.presentation.SettingsComponent
import com.jaegerapps.hansan.screens.words.word_individual.IndividualWordComponent
import com.jaegerapps.hansan.screens.words.word_list.presentation.WordsComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
class RootComponent(
    componentContext: ComponentContext,
    private val appModule: AppModule,
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    private val state = MutableStateFlow(RootState())

    private val scope = CoroutineScope(Dispatchers.IO)
    private var showOnboarding = mutableStateOf(false)
    init {
        scope.launch {
            showOnboarding.value = async { appModule.rootRepo.getOnboarding() }.await()
            state.update { it.copy(
                words = async { appModule.rootRepo.loadWords() }.await()
            ) }
        }
    }



    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = if (showOnboarding.value) Configuration.OnboardingScreen else Configuration.PracticeScreen,
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
                Knower.d("RootComponent", "PracticeComponent is being made.")

                Child.PracticeScreen(
                    PracticeComponent(
                        componentContext = context,
                        tenses = state.value.tenses,
                        repo = appModule.practiceRepo,
                        onNavigate = {
                            onNavigate(it)
                        }
                    )
                )
            }

            Configuration.TensesScreen -> {
                Child.TensesScreen(
                    TensesComponent(
                        componentContext = context,
                        tenses = state.value.tenses,
                        onNavigateToTense = {
                            Knower.d("onNavigateToTense", "Here is the string being passed in $it")
                            navigation.pushNew(Configuration.IndividualTenseScreen(it))

                        },
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
                        currentWord = state.value.words.first { it.baseWord == config.word },
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

            is Configuration.IndividualTenseScreen -> {
                Child.IndividualTenseScreen(
                    IndividualTenseComponent(
                        currentTense = state.value.tenses.first { it.tense.name == config.tense },
                        componentContext = context,
                        onNavigate = {
                            navigation.pop()
                        }
                    )
                )
            }

            Configuration.OnboardingScreen -> {
                Child.OnboardingScreen(
                    OnboardingComponent(
                        componentContext = context,
                        onboardingRepo = appModule.onboardingRepo,
                        kmpNotificationManager = appModule.kmpNotificationManager,
                        permissionChecker = appModule.permissionChecker,
                        onComplete = {
                            scope.launch(Dispatchers.IO) {
                                appModule.rootRepo.toggleOnboarding()
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
        data class TensesScreen(val component: TensesComponent) : Child()
        data class IndividualTenseScreen(val component: IndividualTenseComponent) : Child()
        data class WordsScreen(val component: WordsComponent) : Child()
        data class OnboardingScreen(val component: OnboardingComponent) : Child()
        data class IndividualWordScreen(val component: IndividualWordComponent) : Child()
        data class SettingsScreen(val component: SettingsComponent) : Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object PracticeScreen : Configuration()

        @Serializable
        data object TensesScreen : Configuration()

        @Serializable
        data object WordsScreen : Configuration()

        @Serializable
        data object OnboardingScreen : Configuration()

        @Serializable
        data class IndividualTenseScreen(
            val tense: String,
        ) : Configuration()

        @Serializable
        data class IndividualWordScreen(
            val word: String,
        ) : Configuration()

        @Serializable
        data object SettingsScreen : Configuration()

    }

    private fun onNavigate(route: String) {
        when (route) {
            Routes.LEARN -> navigation.replaceCurrent(Configuration.TensesScreen)
            Routes.PRACTICE -> navigation.replaceAll(Configuration.PracticeScreen)
            Routes.WORDS -> navigation.replaceCurrent(Configuration.WordsScreen)
            Routes.SETTINGS -> navigation.replaceCurrent(Configuration.SettingsScreen)
            else -> navigation.replaceAll(Configuration.PracticeScreen)
        }
    }
}

