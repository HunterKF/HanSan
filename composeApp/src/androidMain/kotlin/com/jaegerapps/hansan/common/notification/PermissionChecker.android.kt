package com.jaegerapps.hansan.common.notification

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d

actual class PermissionChecker(
    private val activity: Activity
) {
    private val REQUEST_CODE = 1001
    actual fun checkPostPermission(): Boolean {
        Knower.d("PermissionChecker", "Checking for permissions.")
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            PermissionChecker.checkSelfPermission(
                activity,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PERMISSION_GRANTED
        } else {
            true
        }
    }

    actual fun requestPermission(): Boolean {
        Knower.d("PermissionChecker", "Requesting permissions.")

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_CODE
                )
                Knower.d("PermissionChecker", "Returning false in SDK >=")

                false // Permission not granted yet
            } else {
                Knower.d("PermissionChecker", "Permission granted!")
                true // Permission already granted
            }
        } else {
            Knower.d("PermissionChecker", "Permission not needed.")
            true // Permission not required for versions below TIRAMISU
        }
    }
}