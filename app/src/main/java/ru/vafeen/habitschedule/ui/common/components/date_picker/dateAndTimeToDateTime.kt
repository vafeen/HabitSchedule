package ru.vafeen.habitschedule.ui.common.components.date_picker

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.MutableState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun isTrueDateAndTime(
    dateTimePickerState: MutableState<LocalDateTime>,
): Boolean {
    return dateTimePickerState.value > LocalDateTime.now()
}
