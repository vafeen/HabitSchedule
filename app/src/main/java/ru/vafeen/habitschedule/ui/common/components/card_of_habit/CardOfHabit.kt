package ru.vafeen.habitschedule.ui.common.components.card_of_habit

import androidx.compose.foundation.background
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
import ru.vafeen.habitschedule.noui.HabitItem
import ru.vafeen.habitschedule.noui.dateAndTime.getStringDateTime
import ru.vafeen.habitschedule.ui.theme.common.HabitScheduleTheme

@Composable
fun HabitItem.CardOfHabit() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
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
            Text(text = title, color = Color.Black)
            Text(text = text)
            Text(text = dateTime.getStringDateTime())
            Text(text = frequency.ruName)
        }

    }
}