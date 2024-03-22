package ru.vafeen.habitschedule.ui.common.components.time_input

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.vafeen.habitschedule.ui.theme.common.HabitScheduleTheme
import ru.vafeen.habitschedule.ui.theme.common.TextSize


@Composable
fun TimePickerItemButton(
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    text: String,
) {

    TextButton(
        modifier = modifier.padding(horizontal = 2.dp),
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            containerColor = HabitScheduleTheme.colors.background,
            contentColor = HabitScheduleTheme.colors.blackInLightAndLightGrayInDark,
            disabledContentColor = HabitScheduleTheme.colors.blackInLightAndLightGrayInDark,
            disabledContainerColor = HabitScheduleTheme.colors.barsColor
        )
    ) {
        Text(
            text = text,
            fontSize = TextSize.medium
//            color = Color.Red
        )
    }

}