package ru.vafeen.habitschedule.noui.db

import ru.vafeen.habitschedule.noui.HabitItem
import ru.vafeen.habitschedule.noui.createHabitItem
import ru.vafeen.habitschedule.noui.db.dao.HabitDateTimeDao
import ru.vafeen.habitschedule.noui.db.dao.HabitItemDao
import ru.vafeen.habitschedule.noui.log.logExecutor


class HabitScheduleRepository(
    private val itemDao: HabitItemDao,
    private val dtDao: HabitDateTimeDao,
) {

    suspend fun getAll(): List<HabitItem> {
        val items = itemDao.getAll()
        logExecutor("MyLog", "items size = ${items.size}")
        val dateTimeList = dtDao.getAll()
        logExecutor("MyLog", "dt list size = ${dateTimeList.size}")
        return if (items.size == dateTimeList.size && items.isNotEmpty()) {

            val result = mutableListOf<HabitItem>()

            for (index in items.indices) {
                result.add(createHabitItem(habitItem = items[index], habitDT = dateTimeList[index]))
            }
            logExecutor("MyLog", "result size = ${result.size}")

            result
        } else {
            listOf()
        }
    }


    suspend fun insert(habitItem: HabitItem) {
        itemDao.insert(habitItem.createHabitItemEntity())

        dtDao.insert(habitItem.createHabitDateTimeEntity())
    }

    suspend fun remove(habitItem: HabitItem) {
        itemDao.remove(habitItem.createHabitItemEntity())

        dtDao.remove(habitItem.createHabitDateTimeEntity())
    }

    suspend fun update(habitItem: HabitItem) {
        itemDao.update(habitItem.createHabitItemEntity())

        dtDao.update(habitItem.createHabitDateTimeEntity())
    }
}