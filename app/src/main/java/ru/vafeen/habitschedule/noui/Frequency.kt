package ru.vafeen.habitschedule.noui

enum class Frequency(val index: Int, val ruName: String) {
    Once(index = 0, ruName = "Разово"),
    EveryDay(index = 1, ruName = "Каждый день"), // here will be time in millis
    EveryWeek(index = 2, ruName = "Каждую неделю"),
    EveryMonth(index = 3, ruName = "Каждый месяц"),
    EveryYear(index = 4, ruName = "Каждый год"),
    Modified(index = 5, ruName = "Другое");

    companion object {
        fun getListFrequency(): List<Frequency> {
            return listOf(Once, EveryDay, EveryWeek, EveryMonth, EveryYear, Modified)
        }
    }
}

data class FrequencyData(
    val frequency: Frequency = Frequency.Once,
    val listOfNumbers: List<Int>? = null,
)