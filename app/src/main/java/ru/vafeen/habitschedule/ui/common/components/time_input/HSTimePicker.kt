package ru.vafeen.habitschedule.ui.common.components.time_input

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HSTimePicker(
    state: MutableState<LocalDateTime>,
) {
    val lazyRowStateHours = rememberLazyListState()

    val lazyRowStateMinutes = rememberLazyListState()

    val cor = rememberCoroutineScope()

    cor.launch {
        lazyRowStateHours.scrollToItem(state.value.hour)
        lazyRowStateMinutes.scrollToItem(state.value.minute)
    }
    Column(modifier = Modifier.fillMaxWidth()) {

        //hours
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            state = lazyRowStateHours
        ) {
            items(count = 24) { index ->
                TimePickerItemButton(
                    onClick = {
                        state.value = state.value.plusHours((index - state.value.hour).toLong())
                    },
                    enabled = state.value.hour != index,
                    text = "$index"
                )
            }
        }

        Spacer(modifier = Modifier.height(3.dp))

        //minutes
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            state = lazyRowStateMinutes
        ) {
            items(count = 60) { index ->
                TimePickerItemButton(
                    onClick = {
                        state.value =
                            state.value.plusMinutes((index - state.value.minute).toLong())
                    },
                    enabled = state.value.minute != index,
                    text = "$index",
                )
            }
        }

    }
}