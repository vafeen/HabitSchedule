package ru.vafeen.habitschedule.noui.db


import androidx.room.Database
import androidx.room.RoomDatabase
import ru.vafeen.habitschedule.noui.db.dao.HabitDateTimeDao
import ru.vafeen.habitschedule.noui.db.dao.HabitItemDao
import ru.vafeen.habitschedule.noui.db.entities.HabitDateTimeEntity
import ru.vafeen.habitschedule.noui.db.entities.HabitItemEntity

@Database(entities = [HabitItemEntity::class, HabitDateTimeEntity::class], version = 1)
abstract class HabitScheduleDatabase : RoomDatabase() {
    abstract fun habitItemDao(): HabitItemDao
    abstract fun habitDateTimeDao(): HabitDateTimeDao
}






