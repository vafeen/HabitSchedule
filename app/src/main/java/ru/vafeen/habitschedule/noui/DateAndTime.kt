package ru.vafeen.habitschedule.noui

import java.time.LocalDateTime
import java.time.Month

object DataObject {
    val ruDaysOfWeek = getRuDaysOfWeek()
    val ruMonths = getRuMonths()
}

private fun getRuMonths(): List<String> {
    return listOf(
        "янв.",
        "фев.",
        "мар.",
        "апр.",
        "мая",
        "июн.",
        "июл.",
        "авг.",
        "сен.",
        "окт.",
        "ноя.",
        "дек.",
    )
}

private fun getRuDaysOfWeek(): List<String> {
    return listOf("пн", "вт", "ср", "чт", "пт", "сб", "вс")
}

fun Int.getMonth(): Month {
    return try {
        Month.entries[this]
    } catch (e: Exception) {
        Month.JANUARY;
    }
}

fun LocalDateTime.getTimeString(): String = "$hour:${
    if (minute > 9) {
        minute
    } else {
        "0$minute"
    }
}"

fun LocalDateTime.getStringDate(): String {
    return "${dayOfMonth}.${getRuMonths()[month.value - 1]}"
}

fun LocalDateTime.getStringDateTime(): String {
    return "${dayOfMonth}.${getRuMonths()[month.value - 1]} $year ${hour}:${minute}"
}

