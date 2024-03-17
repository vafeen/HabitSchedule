package ru.vafeen.habitschedule.ui.common.components.time_input

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HSTimeInput(
    state: TimePickerState,
) {
    TimeInput(state = state)
}