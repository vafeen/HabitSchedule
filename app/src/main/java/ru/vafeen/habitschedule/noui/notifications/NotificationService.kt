package ru.vafeen.habitschedule.noui.notifications

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.app.PendingIntent

import android.content.Intent
import androidx.core.app.NotificationCompat
import ru.vafeen.habitschedule.R
import ru.vafeen.habitschedule.main.MainActivity

const val NOTIFICATION_CHANNEL_ID = "HabitScheduleID"
const val NOTIFICATION_CHANNEL_NAME = "Напоминания"
var NOTIFICATION_ID = 0
const val REQUEST_CODE = 200


class NotificationService(
    private val context: Context,
) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val pendingIntent = PendingIntent.getActivity(
        context,
        REQUEST_CODE,
        Intent(context, MainActivity::class.java),
        PendingIntent.FLAG_IMMUTABLE
    )

    fun showShortNotification(title: String, shortText: String) {
        val notification: Notification =
            NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(shortText)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
        NOTIFICATION_ID += 1
    }

    fun showLongNotification(title: String, bigText: String) {
        val notification: Notification =
            NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
        NOTIFICATION_ID += 1
    }

}
