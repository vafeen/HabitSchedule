package ru.vafeen.habitschedule.noui.dateAndTime

import java.time.LocalDateTime


fun LocalDateTime.getStringDateTime(): String {
    return "${dayOfMonth}.${month.name}.${year}"
}