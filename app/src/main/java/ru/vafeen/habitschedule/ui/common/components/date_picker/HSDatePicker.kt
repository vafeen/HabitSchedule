package ru.vafeen.habitschedule.ui.common.components.date_picker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.vafeen.habitschedule.noui.dateAndTime.getRuDaysOfWeek
import ru.vafeen.habitschedule.noui.dateAndTime.getRuMonths
import ru.vafeen.habitschedule.ui.theme.common.HabitScheduleTheme
import ru.vafeen.habitschedule.ui.theme.common.TextSize
import java.time.LocalDate

@Composable
fun HSDatePicker(
    state: MutableState<LocalDate>,
    onUpdate: (LocalDate) -> Unit
) {
    val lazyListState = rememberLazyListState()

    val today = LocalDate.now()

    LaunchedEffect(key1 = null) {
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

                onUpdate(state.value)
            }) {
                Text(
                    text = "${ruDaysOfWeek[date.dayOfWeek.ordinal]}, ${date.dayOfMonth} ${ruMonths[date.monthValue - 1]} ${date.year}",
                    color = if (state.value == date) {
                        Color.White
                    } else {
                        HabitScheduleTheme.colors.blackInLightAndLightGrayInDark
                    },
                    fontSize = TextSize.big,
                    modifier = Modifier.let {
                        if (state.value == date) {
                            it
                                .background(HabitScheduleTheme.colors.barsColor)
                                .padding(2.dp)
                        } else {
                            it
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(3.dp))
        }
    }
}