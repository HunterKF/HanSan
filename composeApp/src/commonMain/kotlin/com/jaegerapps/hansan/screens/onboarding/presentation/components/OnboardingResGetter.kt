package com.jaegerapps.hansan.screens.onboarding.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.FactCheck
import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources._785_generated_transparent
import hansan.composeapp.generated.resources.icon_mountain
import hansan.composeapp.generated.resources.image_set_goal
import hansan.composeapp.generated.resources.images_onboarding_check
import hansan.composeapp.generated.resources.onboarding_complete_message
import hansan.composeapp.generated.resources.onboarding_complete_title
import hansan.composeapp.generated.resources.onboarding_notifications_message
import hansan.composeapp.generated.resources.onboarding_notifications_title
import hansan.composeapp.generated.resources.onboarding_welcome_message
import hansan.composeapp.generated.resources.onboarding_welcome_title
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@OptIn(ExperimentalResourceApi::class)
class OnboardingStrings {
    companion object {

        fun getImage(screenNumber: OnboardingScreens): DrawableResource {
            return when (screenNumber){
                OnboardingScreens.WELCOME_SCREEN ->  Res.drawable._785_generated_transparent
                OnboardingScreens.REMINDER_SCREEN -> Res.drawable.image_set_goal
                OnboardingScreens.COMPLETE_SCREEN -> Res.drawable.images_onboarding_check
            }
        }

        fun getTitle(screenNumber: OnboardingScreens): StringResource {
            return when (screenNumber) {
                OnboardingScreens.WELCOME_SCREEN -> Res.string.onboarding_welcome_title
                OnboardingScreens.REMINDER_SCREEN -> Res.string.onboarding_notifications_title
                OnboardingScreens.COMPLETE_SCREEN -> Res.string.onboarding_complete_title
            }
        }
        fun getText(screenNumber: OnboardingScreens): StringResource {
            return when (screenNumber) {
                OnboardingScreens.WELCOME_SCREEN -> Res.string.onboarding_welcome_message
                OnboardingScreens.REMINDER_SCREEN -> Res.string.onboarding_notifications_message
                OnboardingScreens.COMPLETE_SCREEN -> Res.string.onboarding_complete_message
            }
        }
    }
}