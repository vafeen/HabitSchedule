package ru.vafeen.habitschedule.main.application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.room.Room
import ru.vafeen.habitschedule.noui.db.HabitScheduleDatabase
import ru.vafeen.habitschedule.noui.notifications.NOTIFICATION_CHANNEL_ID
import ru.vafeen.habitschedule.noui.notifications.NOTIFICATION_CHANNEL_NAME

class HabitApp : Application() {
    companion object {
        var hSDB: HabitScheduleDatabase? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()

        hSDB = Room
            .databaseBuilder(this, HabitScheduleDatabase::class.java, "HabitSchedule.db")
            .build()

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(notificationChannel)

    }

}