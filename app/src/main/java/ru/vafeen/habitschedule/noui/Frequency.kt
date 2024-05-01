package ru.vafeen.habitschedule.noui

enum class Frequency(val index: Int, val ruName: String) {
    Once(index = 0, ruName = "Разово"),
    Daily(index = 1, ruName = "Каждый день"), // here will be time in millis
    Weekly(index = 2, ruName = "Каждую неделю"),
    Monthly(index = 3, ruName = "Каждый месяц"),
    Yearly(index = 4, ruName = "Каждый год"),
    Modified(index = 5, ruName = "Другое");

    companion object {
        fun getListFrequency(): List<Frequency> {
            return listOf(Once, Daily, Weekly, Monthly, Yearly, Modified)
        }
    }
}

data class FrequencyData(
    val frequency: Frequency = Frequency.Once,
    val listOfNumbers: List<Int>? = null,
)