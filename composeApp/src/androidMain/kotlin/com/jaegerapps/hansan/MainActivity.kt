package com.jaegerapps.hansan

import App
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import com.jaegerapps.hansan.di.AppModule
import com.jaegerapps.hansan.root.RootComponent
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.jaegerapps.hansan.common.notification.worker.ReminderWorker
import com.jaegerapps.hansan.common.notification.worker.WorkUseCase
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val appModule = AppModule(sharedPreferences, context = applicationContext, activity = this)
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
