package ru.vafeen.habitschedule.ui.common.components.date_picker

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

fun LocalTime.toMillis(): Long {
    return (hour * 3600 + minute * 60) * 1000L
}

@OptIn(ExperimentalMaterial3Api::class)
fun dateAndTimeToDateTime(
    timePicketState: TimePickerState,
    datePickerState: DatePickerState,
): LocalDateTime {

    val dateValue = datePickerState.selectedDateMillis ?: 0

    val timeValue =
        LocalTime.of(timePicketState.hour, timePicketState.minute).toMillis()

    return LocalDateTime.ofInstant(
        Instant.ofEpochMilli(dateValue + timeValue),
        ZoneId.systemDefault()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
fun isTrueDateAndTime(
    timePicketState: TimePickerState,
    datePickerState: DatePickerState,
): Boolean {
    return dateAndTimeToDateTime(
        timePicketState = timePicketState,
        datePickerState = datePickerState
    ) > LocalDateTime.now()

}
