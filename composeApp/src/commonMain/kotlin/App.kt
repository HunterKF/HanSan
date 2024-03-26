import androidx.compose.runtime.*
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.jaegerapps.hansan.RootComponent
import com.jaegerapps.hansan.core.presentation.HanSanTheme
import com.jaegerapps.hansan.screens.learn.presentation.LearnScreen
import com.jaegerapps.hansan.screens.loading.presentation.LoadingScreen
import com.jaegerapps.hansan.screens.practice.presentation.PracticeScreen
import com.jaegerapps.hansan.screens.words.word_individual.IndividualWordScreen
import com.jaegerapps.hansan.screens.words.word_individual.IndividualWordUiEvent
import com.jaegerapps.hansan.screens.words.word_list.presentation.WordsScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App(
    darkTheme: Boolean,
    root: RootComponent,
) {
    val childStack by root.childStack.subscribeAsState()

    HanSanTheme(
        darkTheme,
    ) {
        var animationSlide by remember { mutableStateOf(slide()) }
        LaunchedEffect(Unit) {
            animationSlide = fade()
        }
        Children(
            stack = childStack,
            animation = stackAnimation(animationSlide)
        ) { child ->
            when (val instance = child.instance) {

                is RootComponent.Child.PracticeScreen -> {
                    val practiceState = instance.component.state.collectAsState()
                    PracticeScreen(
                        state = practiceState.value,
                        onEvent = { instance.component.onEvent(it) })
                }

                is RootComponent.Child.LoadingScreen -> {
                    LoadingScreen()
                }

                is RootComponent.Child.LearnScreen -> {
                    val learnState = instance.component.state.collectAsState()
                    LearnScreen(
                        state = learnState.value,
                        onEvent = { instance.component.onEvent(it) })
                }

                is RootComponent.Child.WordsScreen -> {
                    val wordState = instance.component.state
                    WordsScreen(wordState, onEvent = { instance.component.onEvent(it) })
                }

                is RootComponent.Child.IndividualWordScreen -> {
                    val state = instance.component.state
                    IndividualWordScreen(state, onNavigate = {
                        instance.component.onEvent(
                            IndividualWordUiEvent.OnNavigateBack
                        )
                    })
                }
            }

        }
    }
}