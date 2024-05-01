package ru.vafeen.habitschedule.ui.common.components.time_input

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalTime


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HSTimePicker(
    state: MutableState<LocalTime>,
    onUpdate: (LocalTime) -> Unit
) {
    val lazyRowStateHours = rememberLazyListState()

    val lazyRowStateMinutes = rememberLazyListState()

    val cor = rememberCoroutineScope()

    LaunchedEffect(key1 = null) {
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

                        onUpdate(state.value)
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

                        onUpdate(state.value)
                    },
                    enabled = state.value.minute != index,
                    text = "$index",
                )
            }
        }

    }
}