package ru.vafeen.habitschedule.ui.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.vafeen.habitschedule.ui.screens.Screens
import ru.vafeen.habitschedule.ui.screens.main.Main

@Composable
fun HabitScheduleNavHost() {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = Screens.Main.route) {
        composable(Screens.Main.route) {
            Main()
        }
    }


}