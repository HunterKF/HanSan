import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.jaegerapps.hansan.RootComponent
import com.jaegerapps.hansan.common.components.BottomBarIcon
import com.jaegerapps.hansan.common.util.BottomBarRouteIcon.Companion.routeList
import com.jaegerapps.hansan.core.presentation.HanSanTheme
import com.jaegerapps.hansan.presentation.loading.presentation.LoadingScreen
import com.jaegerapps.hansan.presentation.practice.presentation.PracticeScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App(
    darkTheme: Boolean,
    root: RootComponent
) {
    val childStack by root.childStack.subscribeAsState()

    HanSanTheme(
        darkTheme,
    ) {
        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ) { child ->
            when (val instance = child.instance) {
                is RootComponent.Child.PracticeScreen -> {
                    val practiceState = instance.component.state.collectAsState()
                    PracticeScreen(state = practiceState.value, onEvent = { instance.component.onEvent(it)})
                }

                is RootComponent.Child.LoadingScreen -> {
                    LoadingScreen()
                }
            }

        }
    }
}