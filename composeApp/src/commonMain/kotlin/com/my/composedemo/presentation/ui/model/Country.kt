package com.my.composedemo.presentation.ui.model

import org.jetbrains.compose.resources.DrawableResource
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class Country(
    val name: String,
    val zone: TimeZone,
    val image: DrawableResource
)

fun currentTimeAt(location: String, zone: TimeZone): String {
    fun kotlinx.datetime.LocalTime.formatted() = "$hour:$minute:$second"

    val time = kotlinx.datetime.Clock.System.now()
    val localTime = time.toLocalDateTime(zone).time

    return "The time in $location is ${localTime.formatted()}"
}
