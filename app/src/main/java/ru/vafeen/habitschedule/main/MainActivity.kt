package ru.vafeen.habitschedule.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.vafeen.habitschedule.noui.permissions.RequestNotificationPermission
import ru.vafeen.habitschedule.ui.navigation.HabitScheduleNavHost
import ru.vafeen.habitschedule.ui.theme.common.HabitScheduleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitScheduleTheme {
                RequestNotificationPermission()
                HabitScheduleNavHost()
            }
        }
    }
}

