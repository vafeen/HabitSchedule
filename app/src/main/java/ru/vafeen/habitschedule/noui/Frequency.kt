package ru.vafeen.habitschedule.noui

enum class Frequency(val index: Int) {
    Once(0),
    EveryDay(1), // here will be time in millis
    EveryWeek(2),
    EveryMonth(3),
    EveryYear(4),
    Modified(5);

    companion object {
        fun getListFrequency(): List<Frequency> {
            return listOf(Once, EveryDay, EveryWeek, EveryMonth, EveryYear, Modified)
        }
    }
}
