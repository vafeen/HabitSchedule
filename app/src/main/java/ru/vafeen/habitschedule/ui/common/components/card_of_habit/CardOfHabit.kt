package ru.vafeen.habitschedule.ui.common.components.card_of_habit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.vafeen.habitschedule.noui.DataObject
import ru.vafeen.habitschedule.noui.Frequency
import ru.vafeen.habitschedule.noui.HabitItem
import ru.vafeen.habitschedule.noui.getStringDateTime
import ru.vafeen.habitschedule.noui.getTimeString
import ru.vafeen.habitschedule.ui.theme.common.HabitScheduleTheme

@Composable
fun HabitItem.CardOfHabit(
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = HabitScheduleTheme.colors.habitCardColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 5.dp,
                    vertical = 7.dp
                )
        ) {

//            val title: String = "",
//
//            val text: String = "",
//
//            val dateTime: LocalDateTime = LocalDateTime.now(),
//
//            val isWork: Boolean = true,
//
//            val frequencyData: FrequencyData = FrequencyData()

            Text(text = title, color = Color.Black)

            Text(text = text)

            Text(text = frequencyData.frequency.ruName)

            if (frequencyData.frequency == Frequency.Once || frequencyData.frequency == Frequency.Daily) {

                Text(
                    text = if (frequencyData.frequency == Frequency.Once) {
                        dateTime.getStringDateTime()
                    } else {
                        dateTime.getTimeString()
                    }
                )

            } else if (frequencyData.frequency == Frequency.Weekly) {
                Text(text = frequencyData.listOfNumbers?.map { number ->
                    DataObject.ruDaysOfWeek[number]
                }.toString())
            }

        }

    }
}