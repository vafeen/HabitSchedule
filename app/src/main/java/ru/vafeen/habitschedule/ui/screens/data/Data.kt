package ru.vafeen.habitschedule.ui.screens.data

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import ru.vafeen.habitschedule.main.application.HabitApp
import ru.vafeen.habitschedule.noui.db.HabitScheduleRepository
import ru.vafeen.habitschedule.ui.common.components.bottom_bar.BottomBar

@Composable
fun Data(
    navHostController: NavHostController,
) {
    var db: HabitScheduleRepository? = null
    val itemDao = HabitApp.hSDB?.habitItemDao()
    val dtDao = HabitApp.hSDB?.habitDateTimeDao()
    if (itemDao != null && dtDao != null) {
        db = HabitScheduleRepository(
            itemDao = itemDao,
            dtDao = dtDao
        )
    }

    if (db != null) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {  },
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