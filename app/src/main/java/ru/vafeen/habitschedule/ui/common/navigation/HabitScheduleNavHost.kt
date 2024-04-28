package ru.vafeen.habitschedule.ui.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.vafeen.habitschedule.ui.screens.Screens
import ru.vafeen.habitschedule.ui.screens.data.Data
import ru.vafeen.habitschedule.ui.screens.edit_habit.EditHabit
import ru.vafeen.habitschedule.ui.screens.main.Main

@Composable
fun HabitScheduleNavHost() {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = Screens.Main.route) {
        composable(Screens.Main.route) {
            Main(navHostController = navHostController)
        }
        composable(Screens.Data.route) {
            Data(navHostController = navHostController)
        }
        composable(Screens.EditHabit.route) {
            EditHabit {
                navHostController.popBackStack()
            }
        }
    }

}