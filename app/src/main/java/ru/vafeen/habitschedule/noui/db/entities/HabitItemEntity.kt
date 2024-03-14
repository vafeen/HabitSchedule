package ru.vafeen.habitschedule.noui.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HabitItemEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    val text: String,

    val frequency: Int,
){
    override fun toString(): String {
        return "${id}, ${title}, ${text}, ${frequency},"
    }
}
