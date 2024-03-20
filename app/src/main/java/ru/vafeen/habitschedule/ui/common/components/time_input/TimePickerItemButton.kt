package ru.vafeen.habitschedule.ui.common.components.time_input

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun TimePickerItemButton(
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    text: String,
) {
    Button(
        modifier = modifier.padding(horizontal = 2.dp),
        onClick = onClick,
        enabled = enabled
    ) {
        Text(text = text)
    }
}