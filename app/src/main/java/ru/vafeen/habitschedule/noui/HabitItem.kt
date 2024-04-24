package ru.vafeen.habitschedule.noui

import android.widget.Switch
import ru.vafeen.habitschedule.noui.dateAndTime.getMonth
import ru.vafeen.habitschedule.noui.db.entities.HabitDateTimeEntity
import ru.vafeen.habitschedule.noui.db.entities.HabitItemEntity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class HabitItem(
    val id: Int = 0,
    val title: String = "",
    val text: String = "",
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val frequency: Frequency = Frequency.Once,
    val isWork: Boolean = true
) {
    fun createHabitItemEntity(): HabitItemEntity = HabitItemEntity(
        id = id,
        title = title,
        text = text, frequency = frequency.index
    )

    fun createHabitDateTimeEntity(): HabitDateTimeEntity = HabitDateTimeEntity(
        id = id, datetimeMinutes = dateTime.minute,
        datetimeHours = dateTime.hour,
        dateTimeDay = dateTime.dayOfMonth,
        dateTimeMonth = dateTime.month.value,
        dateTimeYear = dateTime.year
    )

    override fun toString(): String {
        return "$id, ${title}, ${text}, ${dateTime.hour}, ${dateTime.minute}"
    }
}

fun createHabitItem(habitItem: HabitItemEntity, habitDT: HabitDateTimeEntity): HabitItem =
    HabitItem(
        id = habitItem.id,
        title = habitItem.title,
        text = habitItem.text,
        dateTime = LocalDateTime.of(
            LocalDate.of(
                habitDT.dateTimeYear,
                habitDT.dateTimeMonth.getMonth(),
                habitDT.dateTimeDay
            ),
            LocalTime.of(habitDT.datetimeHours, habitDT.datetimeMinutes)
        )
    )
