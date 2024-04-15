package ru.vafeen.habitschedule.noui.log

enum class LogType(val value: String) {
    Database("Database");

    companion object {
        const val defaultName = "App"
    }

}