package ru.vafeen.habitschedule.ui.screens.edit_habit

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.vafeen.habitschedule.main.application.HabitApp
import ru.vafeen.habitschedule.noui.HabitItem
import ru.vafeen.habitschedule.noui.dateAndTime.ListOfData.ruMonths
import ru.vafeen.habitschedule.ui.common.components.date_picker.HSDatePicker
import ru.vafeen.habitschedule.ui.common.components.frequency_picker.FrequencyPicker
import ru.vafeen.habitschedule.ui.common.components.time_input.HSTimePicker
import ru.vafeen.habitschedule.ui.common.components.time_input.getTimeString
import ru.vafeen.habitschedule.ui.theme.common.HabitScheduleTheme
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun EditHabit(
    onClickToGoBack: () -> Unit
) {
    val cor = rememberCoroutineScope()

    val index = HabitApp.indexOfEditableHabit

    val tfColors = TextFieldDefaults.colors(
        focusedTextColor = HabitScheduleTheme.colors.blackInLightAndLightGrayInDark,
        unfocusedTextColor = HabitScheduleTheme.colors.blackInLightAndLightGrayInDark,
        unfocusedContainerColor = HabitScheduleTheme.colors.background,
        focusedContainerColor = HabitScheduleTheme.colors.background,
    )

    val item = remember {
        mutableStateOf(HabitItem())
    }

    var datePickerState = remember {
        mutableStateOf(LocalDate.now())
    }

    var timePickerState = remember {
        mutableStateOf(LocalTime.now())
    }

    if (index != null) {
        LaunchedEffect(key1 = null) {
            item.value = HabitApp.HabitItemRepository.getByIndex(index)
        }

        datePickerState = remember {
            mutableStateOf(
                LocalDate.of(
                    item.value.dateTime.year,
                    item.value.dateTime.month,
                    item.value.dateTime.dayOfMonth
                )
            )
        }

        timePickerState = remember {
            mutableStateOf(
                LocalTime.of(
                    item.value.dateTime.hour,
                    item.value.dateTime.minute
                )
            )
        }
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clickable(onClick = onClickToGoBack),
                containerColor = HabitScheduleTheme.colors.barsColor
            ) {
                val colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Gray,
                    unselectedIconColor = Color.Black,
                    indicatorColor = HabitScheduleTheme.colors.barsColor
                )

                NavigationBarItem(
                    selected = false, onClick = onClickToGoBack, icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "go back"
                        )
                    },
                    colors = colors
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HabitScheduleTheme.colors.background)
                .verticalScroll(rememberScrollState())
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            OutlinedTextField(
                value = item.value.title,
                onValueChange = { item.value = item.value.copy(title = it) },
                colors = tfColors,
                label = { Text(text = "Название") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = item.value.text,
                onValueChange = { item.value = item.value.copy(text = it) },
                colors = tfColors,
                label = { Text(text = "Описание") })

            Spacer(modifier = Modifier.height(5.dp))

            HSTimePicker(state = timePickerState) {
                item.value = item.value.copy(
                    dateTime = LocalDateTime.of(
                        datePickerState.value,
                        timePickerState.value
                    )
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            HSDatePicker(state = datePickerState) {
                item.value = item.value.copy(
                    dateTime = LocalDateTime.of(
                        datePickerState.value,
                        timePickerState.value
                    )
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            FrequencyPicker(item = item)

            Spacer(modifier = Modifier.height(5.dp))

            Button(
                onClick = {
                    cor.launch {
                        val now = LocalDateTime.now()

                        val dt = LocalDateTime.of(
                            datePickerState.value,
                            timePickerState.value
                        )
                        Log.d("App", "1")
                        item.value = item.value.copy(
                            dateTime = if (now >= dt) {
                                dt.plusMinutes(2L)
                            } else {
                                dt
                            }
                        )

                        Log.d("App", "2")
                        HabitApp.HabitItemRepository.update(
                            habitItem = item.value
                        )
                    }

                    onClickToGoBack()
                    Log.d("App", "3")

                },
                shape = RoundedCornerShape(7.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = HabitScheduleTheme.colors.barsColor,
                    contentColor = HabitScheduleTheme.colors.whiteInDarkAndBlackInLight
                )
            ) {
                val dt = item.value.dateTime

                Text(
                    text = "Отправить ${dt.dayOfMonth} " +
                            "${ruMonths[dt.monthValue - 1]} ${dt.year} в ${dt.getTimeString()}",
                    color = HabitScheduleTheme.colors.blackInLightAndLightGrayInDark
                )

            }
        }
    }


}