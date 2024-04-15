package ru.vafeen.habitschedule.noui.log

import android.util.Log

fun logExecutor(suffixTag: String? = null , message: String) {

    // "when" for switching on/off some types of logs :)
    when (suffixTag) {

        LogType.Database.value -> {
            Log.e(LogType.defaultName + suffixTag, message)
        }

        else -> {
            Log.e(suffixTag, message)
        }
    }



}
