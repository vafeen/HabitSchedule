package ru.vafeen.habitschedule.ui.common.components.date_picker

import androidx.compose.runtime.MutableState
import java.time.LocalDateTime

fun isTrueDateAndTime(
    dateTimePickerState: MutableState<LocalDateTime>,
): Boolean {
    return dateTimePickerState.value > LocalDateTime.now()
}
