package ru.vafeen.habitschedule.ui.common.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.vafeen.habitschedule.noui.HabitItem
import ru.vafeen.habitschedule.ui.common.components.date_picker.HSDatePicker
import ru.vafeen.habitschedule.ui.common.components.time_input.HSTimePicker
import ru.vafeen.habitschedule.ui.theme.common.HabitScheduleTheme
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun AddEditHabitDialog(
    item: MutableState<HabitItem>,
    onDismissRequest: () -> Unit,
    mainButtonAction: (item: HabitItem) -> Unit,
    mainButtonText: @Composable (item: HabitItem) -> Unit,
) {

    val tfColors = TextFieldDefaults.colors(
        focusedTextColor = HabitScheduleTheme.colors.blackInLightAndLightGrayInDark,
        unfocusedTextColor = HabitScheduleTheme.colors.blackInLightAndLightGrayInDark,
        unfocusedContainerColor = HabitScheduleTheme.colors.background,
        focusedContainerColor = HabitScheduleTheme.colors.background,
    )

    val datePickerState = remember {
        mutableStateOf(
            LocalDate.of(
                item.value.dateTime.year,
                item.value.dateTime.month,
                item.value.dateTime.dayOfMonth
            )
        )
    }

    val timePickerState = remember {
        mutableStateOf(LocalTime.of(item.value.dateTime.hour, item.value.dateTime.minute))
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .size(500.dp)
                .background(HabitScheduleTheme.colors.background)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = onDismissRequest,
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(
                    imageVector = Icons.Default.Close, contentDescription = "close dialog",
                    tint = Color.White,
                    modifier = Modifier.size(50.dp)
                )
            }

            OutlinedTextField(
                value = item.value.title,
                onValueChange = { item.value = item.value.copy(title = it) },
                colors = tfColors,
                label = { Text(text = "Название") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(value = item.value.text,
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

            Button(
                onClick = {
                    val now = LocalDateTime.now()

                    val dt = LocalDateTime.of(
                        datePickerState.value,
                        timePickerState.value
                    )

                    item.value = item.value.copy(
                        dateTime = if (now >= dt) {
                            dt.plusMinutes(2L)
                        } else {
                            dt
                        }
                    )

                    mainButtonAction(item.value)

                    onDismissRequest()

                }, shape = RoundedCornerShape(7.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = HabitScheduleTheme.colors.barsColor,
                    contentColor = HabitScheduleTheme.colors.whiteInDarkAndBlackInLight
                )
            ) {
                mainButtonText(item = item.value)
            }
        }
    }

}