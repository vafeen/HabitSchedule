package ru.vafeen.habitschedule.noui.dateAndTime

import java.time.Month

@OptIn(ExperimentalStdlibApi::class)
fun Int.getMonth(): Month {
    return try {
        Month.entries[this]
    } catch (e: Exception) {
        Month.JANUARY;
    }
}
