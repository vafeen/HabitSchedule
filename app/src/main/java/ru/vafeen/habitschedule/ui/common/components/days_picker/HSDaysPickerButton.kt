package ru.vafeen.habitschedule.ui.common.components.days_picker

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun HSDaysPickerButton(
    day: String,
    checked: Boolean,
    onCheckedChangeEvent: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = day)
        Checkbox(checked = checked, onCheckedChange = onCheckedChangeEvent)
    }
}