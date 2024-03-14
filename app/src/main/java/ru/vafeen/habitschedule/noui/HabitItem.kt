package ru.vafeen.habitschedule.noui

import java.time.LocalDateTime

data class HabitItem(
    val id: Int,
    val title: String,
    val text: String,
    val dateTime: LocalDateTime,
    val frequency: Frequency,
) {
}
