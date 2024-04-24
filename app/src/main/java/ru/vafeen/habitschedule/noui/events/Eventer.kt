package ru.vafeen.habitschedule.noui.events

import android.content.Context
import ru.vafeen.habitschedule.main.application.HabitApp
import ru.vafeen.habitschedule.noui.Frequency
import ru.vafeen.habitschedule.noui.HabitItem
import ru.vafeen.habitschedule.noui.alarm_manager.SchedulerForNotifyAboutHabits

class Eventer(
    context: Context
) {
   private val sheduler = SchedulerForNotifyAboutHabits(context = context)
   private val repository = HabitApp.HabitItemRepository

    private fun HabitItem.switch() {
        if (isWork) {
            if (frequency == Frequency.Once) {
                sheduler.scheduleOnce(habitItem = this)
            } else {
                sheduler.scheduleWithRepeating(habitItem = this)
            }
        } else {
            sheduler.cancel(habitItem = this)
        }
    }

    suspend fun addEvent(habitItem: HabitItem) {
        repository.insert(habitItem = habitItem)

        habitItem.switch()
    }

    suspend fun removeEvent(habitItem: HabitItem) {
        habitItem.switch()

        repository.remove(habitItem = habitItem)
    }

    suspend fun updateEvent(habitItem: HabitItem) {
        repository.update(habitItem = habitItem)

        habitItem.switch()
    }
}