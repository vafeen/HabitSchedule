package ru.vafeen.habitschedule.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import ru.vafeen.habitschedule.main.application.HabitApp
import ru.vafeen.habitschedule.noui.db.DatabaseRepository
import ru.vafeen.habitschedule.ui.common.components.bottom_bar.BottomBar
import ru.vafeen.habitschedule.ui.screens.Screens

@Composable
fun Main(
    navHostController: NavHostController,
) {
    var db: DatabaseRepository? = null
    val itemDao = HabitApp.hSDB?.habitItemDao()
    val dtDao = HabitApp.hSDB?.habitDateTimeDao()
    if (itemDao != null && dtDao != null) {
        db = DatabaseRepository(
            itemDao = itemDao,
            dtDao = dtDao
        )
    }
    if (db != null) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomBar(selected1 = true,
                    onClickToScreen2 = {
                        navHostController.navigate(Screens.Data.route)
                    })
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

            }

        }

    }
}