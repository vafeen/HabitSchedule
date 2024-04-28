package ru.vafeen.habitschedule.noui.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.vafeen.habitschedule.noui.db.entities.HabitItemEntity

@Dao
interface HabitItemDao {

    @Query("select * from habititementity")
    suspend fun getAll(): List<HabitItemEntity>

    @Query("select * from habititementity where id=:index")
    suspend fun getByIndex(index: Int): HabitItemEntity

    @Insert
    suspend fun insert(habitItem: HabitItemEntity)

    @Delete
    suspend fun remove(habitItem: HabitItemEntity)

    @Update
    suspend fun update(habitItem: HabitItemEntity)
}