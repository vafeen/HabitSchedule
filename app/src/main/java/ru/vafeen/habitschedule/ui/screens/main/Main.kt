package ru.vafeen.habitschedule.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.flow
import ru.vafeen.habitschedule.main.application.HabitApp
import ru.vafeen.habitschedule.noui.Frequency
import ru.vafeen.habitschedule.noui.HabitItem
import ru.vafeen.habitschedule.noui.log.LogType
import ru.vafeen.habitschedule.noui.log.logExecutor
import ru.vafeen.habitschedule.ui.common.components.bottom_bar.BottomBar
import ru.vafeen.habitschedule.ui.common.components.card_of_habit.CardOfHabit
import ru.vafeen.habitschedule.ui.screens.Screens
import ru.vafeen.habitschedule.ui.theme.common.HabitScheduleTheme
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(
    navHostController: NavHostController,
) {
    val db = HabitApp.HabitItemRepository

    val dateTime by remember {
        mutableStateOf(LocalDateTime.now())
    }

    var listik by remember {
        mutableStateOf(listOf<HabitItem>())
    }

    val itemsList by remember {
        mutableStateOf(flow {
            emit(db.getAll())
        })
    }

    LaunchedEffect(key1 = null) {
        itemsList.collect { listikInDB ->

            listik = listikInDB.filter {

                val fr = it.frequencyData

                when {
                    fr.frequency == Frequency.Daily -> {
                        true
                    }

                    fr.frequency == Frequency.Weekly
                            && fr.listOfNumbers
                        ?.contains(dateTime.dayOfWeek.value - 1) == true -> {
                        true
                    }

                    fr.frequency == Frequency.Monthly && fr.listOfNumbers
                        ?.contains(dateTime.dayOfMonth - 1) == true -> {
                        true
                    }

                    fr.frequency == Frequency.Once && it.dateTime.dayOfYear == dateTime.dayOfYear -> {
                        true
                    }

                    else -> {
                        false
                    }
                }
            }
        }

        logExecutor(suffixTag = LogType.Database.value, message = "обновление в launchedEffect")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),

                        horizontalArrangement = Arrangement.Center,

                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(text = "Сегодня", color = Color.Black)

                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = HabitScheduleTheme.colors.barsColor
                )
            )
        },
        bottomBar = {
            BottomBar(selected1 = true,

                onClickToScreen2 = {

                    navHostController.navigate(Screens.Data.route)

                })
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(items = listik) {
                it.CardOfHabit {
                    null
                }
            }
        }
    }


}