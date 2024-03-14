package ru.vafeen.habitschedule.noui.alarm_manager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import ru.vafeen.habitschedule.noui.notifications.NotificationService

class ScheduleReceiverForNotifyAboutHabits : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val id = intent?.getIntExtra("EXTRA_ID", -1) ?: -1
        Log.e("rec", "сработало?")
        if (context != null)
            if (id >= 0) {
                // here will be the code with gettingEntityByIndex
                NotificationService(context).showShortNotification(title = "title", shortText = "text")
            } else {
                NotificationService(context).showShortNotification(title = "title", shortText = "id is -1")
            }
    }
}