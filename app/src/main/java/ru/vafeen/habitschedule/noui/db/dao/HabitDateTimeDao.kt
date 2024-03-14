package ru.vafeen.habitschedule.noui.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.vafeen.habitschedule.noui.db.entities.HabitDateTimeEntity

@Dao
interface HabitDateTimeDao {

    @Query("select * from habitdatetimeentity")
    suspend fun getAll(): List<HabitDateTimeEntity>

    @Insert
    suspend fun insert(habitItemDateTime: HabitDateTimeEntity)

    @Delete
    suspend fun remove(habitItemDateTime: HabitDateTimeEntity)

    @Update
    suspend fun update(habitItemDateTime: HabitDateTimeEntity)

}