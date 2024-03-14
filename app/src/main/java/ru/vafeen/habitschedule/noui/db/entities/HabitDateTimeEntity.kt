package ru.vafeen.habitschedule.noui.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class HabitDateTimeEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val datetimeMinutes: Int,

    val datetimeHours: Int,

    val dateTimeDay: Int,

    val dateTimeMonth: Int,

    val dateTimeYear: Int,

    ) {
    override fun toString(): String {
        return "$id, $datetimeMinutes, $datetimeHours, $dateTimeDay, $dateTimeMonth, $dateTimeYear"
    }
}