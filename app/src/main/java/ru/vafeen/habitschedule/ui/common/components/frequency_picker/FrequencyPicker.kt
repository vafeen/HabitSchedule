package ru.vafeen.habitschedule.ui.common.components.frequency_picker

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.vafeen.habitschedule.noui.DataObject.ruDaysOfWeek
import ru.vafeen.habitschedule.noui.Frequency
import ru.vafeen.habitschedule.noui.FrequencyData
import ru.vafeen.habitschedule.noui.HabitItem

import ru.vafeen.habitschedule.ui.common.components.days_picker.HSDaysPickerButton

@Composable
fun FrequencyPicker(
    item: MutableState<HabitItem>
) {
    var frequency by remember {
        mutableStateOf(item.value.frequencyData.frequency)
    }

    val daysOfWeek = ruDaysOfWeek

    var numbers by remember {
        mutableStateOf(listOf<Int>())
    }

    var isExpanded by remember {
        mutableStateOf(false)
    }

    val onDismissRequest = { isExpanded = false }

    val freqList = Frequency.getListFrequency().subList(0, 3)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Очередность события:")

        Spacer(modifier = Modifier.height(5.dp))

        Box() {
            Text(text = frequency.ruName, modifier = Modifier.clickable {
                isExpanded = !isExpanded
            })

            DropdownMenu(expanded = isExpanded, onDismissRequest = onDismissRequest) {
                for (frequencyI in freqList) {
                    Text(text = frequencyI.ruName, modifier = Modifier.clickable {
                        frequency = frequencyI

                        onDismissRequest()

                        item.value = item.value.copy(
                            frequencyData = FrequencyData(
                                frequency = frequencyI,
                                listOfNumbers = null
                            )
                        )
                    })
                }
            }
        }

        when (frequency) {
            Frequency.Weekly -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (indexOfDay in 0..daysOfWeek.lastIndex) {
                        HSDaysPickerButton(
                            day = daysOfWeek[indexOfDay],
                            checked = indexOfDay in numbers
                        ) {
                            numbers = when (it) {
                                true -> {
                                    numbers.plus(indexOfDay)
                                }

                                false -> {
                                    numbers.minus(indexOfDay)
                                }
                            }
                            Log.d("APPP", "$numbers")

                        }
                    }
                }
            }

            Frequency.Monthly -> {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(count = 31) { index ->
                        HSDaysPickerButton(
                            day = "$index",
                            checked = index in numbers
                        ) {
                            numbers = when (it) {
                                true -> {
                                    numbers.plus(index)
                                }

                                false -> {
                                    numbers.minus(index)
                                }
                            }

                        }
                    }
                }
            }

            else -> {

            }
        }
    }


}