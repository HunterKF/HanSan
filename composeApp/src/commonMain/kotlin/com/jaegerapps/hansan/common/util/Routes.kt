package com.jaegerapps.hansan.common.util

import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources.icon_list
import hansan.composeapp.generated.resources.icon_mountain
import hansan.composeapp.generated.resources.icon_quotes
import hansan.composeapp.generated.resources.icon_settings
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

object Routes {
    const val PRACTICE = "Practice"
    const val WORDS = "Words"
    const val LEARN = "Learn"
    const val SETTINGS = "Settings"
}

@OptIn(ExperimentalResourceApi::class)
data class BottomBarRouteIcon(
    val route: String,
    val icon: DrawableResource
) {
    companion object {
        val routeList = listOf(
            BottomBarRouteIcon(
                route = Routes.PRACTICE,
                icon = Res.drawable.icon_mountain
            ),

            BottomBarRouteIcon(
                route = Routes.WORDS,
                icon = Res.drawable.icon_list
            ),

            BottomBarRouteIcon(
                route = Routes.LEARN,
                icon = Res.drawable.icon_quotes
            ),

            BottomBarRouteIcon(
                route = Routes.SETTINGS,
                icon = Res.drawable.icon_settings
            ),

        )
    }
}