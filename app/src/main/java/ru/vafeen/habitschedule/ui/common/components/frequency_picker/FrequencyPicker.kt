package ru.vafeen.habitschedule.ui.common.components.frequency_picker

import android.service.autofill.OnClickAction
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ru.vafeen.habitschedule.noui.Frequency

@Composable
fun FrequencyPicker(
    initialValue: Frequency,
    onClickAction: (Frequency) -> Unit,
) {
    var frequency by remember {
        mutableStateOf(initialValue)
    }

    var isExpanded by remember {
        mutableStateOf(false)
    }

    val onDismissRequest = { isExpanded = false }

    val freqList = Frequency.getListFrequency()

    Box() {
        Text(text = frequency.ruName)

        DropdownMenu(expanded = isExpanded, onDismissRequest = onDismissRequest) {
            for (frequencyI in freqList) {
                Text(text = frequencyI.ruName, modifier = Modifier.clickable {
                    frequency = frequencyI
                    onDismissRequest()
                    onClickAction(frequencyI)
                })
            }
        }
    }
}