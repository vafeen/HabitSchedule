package ru.vafeen.habitschedule.ui.common.components.date_picker

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.vafeen.habitschedule.noui.dateAndTime.getRuDaysOfWeek
import ru.vafeen.habitschedule.noui.dateAndTime.getRuMonths
import ru.vafeen.habitschedule.ui.theme.common.HabitScheduleTheme
import ru.vafeen.habitschedule.ui.theme.common.TextSize
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HSDatePicker(
    state: MutableState<LocalDateTime>,
) {
    val cor = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val today = LocalDateTime.now()
    cor.launch {
        lazyListState.scrollToItem(index = today.dayOfYear - state.value.dayOfYear)
    }
    val ruDaysOfWeek = getRuDaysOfWeek()
    val ruMonths = getRuMonths()
    LazyColumn(
        state = lazyListState,
        modifier = Modifier.height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(count = 366) { index ->
            val date = today.plusDays(index.toLong())
            Row(modifier = Modifier.clickable {
                state.value = date
            }) {
                if (state.value == date) {

                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = "selected date",
                        tint = HabitScheduleTheme.colors.textColor,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(3.dp))
                }
                Text(
                    text = "${ruDaysOfWeek[date.dayOfWeek.ordinal]}, ${date.dayOfMonth} ${ruMonths[date.monthValue - 1]} ${date.year}",
                    color = HabitScheduleTheme.colors.textColor,
                    fontSize = TextSize.medium
                )

            }
            Spacer(modifier = Modifier.height(3.dp))
        }
    }
}