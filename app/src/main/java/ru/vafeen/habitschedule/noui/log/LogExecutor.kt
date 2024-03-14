package ru.vafeen.habitschedule.noui.log

import android.util.Log

fun logExecutor(tag: String, message: String) {
    val show = true
    if (show) {
        Log.e(tag, message)
    }

}