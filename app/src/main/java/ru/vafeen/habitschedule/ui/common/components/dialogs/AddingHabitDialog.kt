package ru.vafeen.habitschedule.ui.common.components.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import ru.vafeen.habitschedule.noui.HabitItem
import ru.vafeen.habitschedule.noui.dateAndTime.getRuMonths
import ru.vafeen.habitschedule.ui.common.components.time_input.getTimeString

@Composable
fun AddingHabitDialog(
    onDismissRequest: () -> Unit, onAddNewItem: (item: HabitItem) -> Unit,
) {
    val ruMonths = getRuMonths()

    val newItem = remember {
        mutableStateOf(HabitItem())
    }

    AddEditHabitDialog(
        item = newItem,
        onDismissRequest = onDismissRequest,
        mainButtonAction = onAddNewItem,
        mainButtonText = { item ->
            val dt = item.dateTime
            "Отправить ${dt.dayOfMonth} ${ruMonths[dt.monthValue]} ${dt.year} в ${dt.getTimeString()}"
        }
    )

}