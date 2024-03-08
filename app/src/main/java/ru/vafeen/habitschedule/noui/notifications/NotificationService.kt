package ru.vafeen.habitschedule.noui.notifications

import android.annotation.SuppressLint
import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.app.PendingIntent

import android.content.Intent
import androidx.core.app.NotificationCompat
import ru.vafeen.habitschedule.R
import ru.vafeen.habitschedule.main.MainActivity

val NOTIFICATION_CHANNEL_ID = "HabitScheduleID"
val NOTIFICATION_CHANNEL_NAME = "HabitScheduleChannel"
var NOTIFICATION_ID = 0
val REQUEST_CODE = 200


class NotificationService(
    private val context: Context,
) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val myIntent = Intent(context, MainActivity::class.java)

    private val pendingIntent = PendingIntent.getActivity(
        context, REQUEST_CODE,
        myIntent,
        PendingIntent.FLAG_IMMUTABLE
    )

    fun showNotification(title: String, text: String) {
        val notification: Notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
//                .setContentText(text)
            .setStyle(NotificationCompat.BigTextStyle().bigText(text))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
        NOTIFICATION_ID += 1
    }
}
