package ru.vafeen.habitschedule.noui.dateAndTime

import java.time.LocalDateTime


fun LocalDateTime.getStringDateTime(): String {
    return "${dayOfMonth}.${getRuMonths()[month.value - 1]} $year ${hour}:${minute}"
}