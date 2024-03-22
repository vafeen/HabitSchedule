package ru.vafeen.habitschedule.ui.common.components.time_input

import java.time.LocalDateTime
import java.time.LocalTime

fun LocalTime.getTimeString(): String = "$hour:${
    if (minute > 9) {
        minute
    } else {
        "0$minute"
    }
}"
fun LocalDateTime.getTimeString(): String = "$hour:${
    if (minute > 9) {
        minute
    } else {
        "0$minute"
    }
}"
