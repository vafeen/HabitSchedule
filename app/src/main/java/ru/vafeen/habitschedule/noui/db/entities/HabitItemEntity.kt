package ru.vafeen.habitschedule.noui.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HabitItemEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    val text: String,

    val isWork: Boolean,

    val frequencyData: String
) {
}
