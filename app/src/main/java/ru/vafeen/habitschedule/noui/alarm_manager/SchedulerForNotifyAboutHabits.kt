package ru.vafeen.habitschedule.noui.alarm_manager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import ru.vafeen.habitschedule.noui.Frequency
import ru.vafeen.habitschedule.noui.HabitItem
import java.time.Duration
import java.time.ZoneId


class SchedulerForNotifyAboutHabits(
    private val context: Context,
) {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    fun updateSchedule(habitItem: HabitItem) {
        cancel(habitItem)

        if (habitItem.frequency == Frequency.Once) {
            scheduleOnce(habitItem = habitItem)
        } else {
            scheduleWithRepeating(habitItem = habitItem)
        }
    }

    fun scheduleOnce(habitItem: HabitItem) {
        val intent = Intent(context, ScheduleReceiverForNotifyAboutHabits::class.java).apply {
            putExtra(ExtraValues.ItemID.key, habitItem.id)
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            habitItem.dateTime.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000L,
            PendingIntent.getBroadcast(
                context,
                habitItem.id,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )

    }

    fun scheduleWithRepeating(habitItem: HabitItem) {
        val intent = Intent(context, ScheduleReceiverForNotifyAboutHabits::class.java).apply {
            putExtra(ExtraValues.ItemID.key, habitItem.id)
        }


        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            habitItem.dateTime.atZone(ZoneId.systemDefault())
                .toEpochSecond() * 1000L,
            habitItem.frequency.timeRepeat,
            PendingIntent.getBroadcast(
                context,
                habitItem.id,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    fun cancel(habitItem: HabitItem) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                habitItem.id,
                Intent(context, ScheduleReceiverForNotifyAboutHabits::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}