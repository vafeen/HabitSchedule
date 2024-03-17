package ru.vafeen.habitschedule.ui.common.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.vafeen.habitschedule.noui.HabitItem
import ru.vafeen.habitschedule.ui.common.components.date_picker.HSDatePicker
import ru.vafeen.habitschedule.ui.common.components.date_picker.isTrueDateAndTime
import ru.vafeen.habitschedule.ui.common.components.time_input.HSTimeInput
import ru.vafeen.habitschedule.ui.theme.common.HabitScheduleTheme
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingHabitDialog(
    onDismissRequest: () -> Unit, onAddNewItem: (item: HabitItem) -> Unit,
) {
    var newItem by remember {
        mutableStateOf(HabitItem())
    }
    val tfColors = TextFieldDefaults.colors(
        focusedTextColor = HabitScheduleTheme.colors.textColor,
        unfocusedTextColor = HabitScheduleTheme.colors.textColor,
        unfocusedContainerColor = HabitScheduleTheme.colors.background,
        focusedContainerColor = HabitScheduleTheme.colors.background,
    )
    val timePicketState = rememberTimePickerState(
        is24Hour = true
    )
    val datePickerState = remember {
        mutableStateOf(LocalDate.now())
    }
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
//            .padding(paddingValues = paddingValues)
                .size(500.dp)
                .background(HabitScheduleTheme.colors.background)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                value = newItem.title,
                onValueChange = { newItem = newItem.copy(title = it) },
                colors = tfColors,
                label = { Text(text = "Название") },
                singleLine = true
            )
            OutlinedTextField(
                value = newItem.text,
                onValueChange = { newItem = newItem.copy(text = it) },
                colors = tfColors,
                label = { Text(text = "Описание") }
            )
            HSTimeInput(state = timePicketState)
            HSDatePicker(state = datePickerState)
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        newItem = newItem.copy(
                            dateTime = LocalDateTime.of(
                                datePickerState.value,
                                LocalTime.of(timePicketState.hour, timePicketState.minute)
                            )
                        )
                    },
                    enabled = isTrueDateAndTime(
                        datePickerState = datePickerState,
                        timePicketState = timePicketState,
                    )
                ) {
                    Text(text = "add")
                }
            }

        }
    }

}