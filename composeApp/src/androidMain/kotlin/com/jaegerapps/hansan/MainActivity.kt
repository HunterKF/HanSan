package com.jaegerapps.hansan

import App
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
            WindowCompat.setDecorFitsSystemWindows(window, false)
            App(
                darkTheme = isSystemInDarkTheme(),
                root = root
            )
        }
    }

}
