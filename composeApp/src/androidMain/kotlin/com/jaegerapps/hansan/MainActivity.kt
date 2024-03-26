package com.jaegerapps.hansan

import App
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import com.jaegerapps.hansan.di.AppModule
import java.lang.Character.UnicodeBlock

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val appModule = AppModule(sharedPreferences)
        val root = retainedComponent {
            RootComponent(it, appModule)
        }
        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            App(
                darkTheme = isSystemInDarkTheme(),
                root = root
            )
        }
    }
}
