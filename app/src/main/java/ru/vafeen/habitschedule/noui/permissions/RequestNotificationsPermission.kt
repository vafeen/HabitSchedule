package ru.vafeen.habitschedule.noui.permissions

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@Composable
fun RequestNotificationPermission() {
    val context = LocalContext.current

    val permissionState =
        ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
    if (permissionState == PackageManager.PERMISSION_DENIED) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            1
        )
    }
}