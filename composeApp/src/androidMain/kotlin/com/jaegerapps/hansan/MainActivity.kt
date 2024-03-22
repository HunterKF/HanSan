package com.jaegerapps.hansan

import App
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import com.jaegerapps.hansan.di.AppModule

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
            App(
                darkTheme = isSystemInDarkTheme(),
                root = root
            )
        }
    }
}
