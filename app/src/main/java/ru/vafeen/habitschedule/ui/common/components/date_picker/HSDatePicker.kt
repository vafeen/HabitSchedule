package ru.vafeen.habitschedule.ui.common.components.date_picker

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import java.time.LocalDateTime
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HSDatePicker(
    state: DatePickerState,
) {
    DatePicker(
        state = state,
        showModeToggle = false,
        title = null,
        headline = null,
        dateValidator = { dt ->
            dt > LocalDateTime.now().atZone(ZoneId.systemDefault())
                .toEpochSecond() * 1000L
        },
    )
}