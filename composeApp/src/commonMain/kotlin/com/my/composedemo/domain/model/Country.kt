package com.my.composedemo.domain.model

import org.jetbrains.compose.resources.DrawableResource
import kotlinx.datetime.TimeZone
import composedemo.composeapp.generated.resources.Res
import composedemo.composeapp.generated.resources.jp
import composedemo.composeapp.generated.resources.fr
import composedemo.composeapp.generated.resources.mx
import composedemo.composeapp.generated.resources.id
import composedemo.composeapp.generated.resources.eg

/**
 * ドメインエンティティ: Country
 * ビジネスロジックに必要な国情報を表現
 */
data class Country(
    val id: String,
    val name: String,
    val timeZone: TimeZone,
    val image: DrawableResource,
    val isFavorite: Boolean = false
) {
    companion object {
        fun createDefaultCountries(): List<Country> = listOf(
            Country(
                id = "japan",
                name = "Japan",
                timeZone = TimeZone.of("Asia/Tokyo"),
                image = Res.drawable.jp
            ),
            Country(
                id = "france",
                name = "France", 
                timeZone = TimeZone.of("Europe/Paris"),
                image = Res.drawable.fr
            ),
            Country(
                id = "mexico",
                name = "Mexico",
                timeZone = TimeZone.of("America/Mexico_City"),
                image = Res.drawable.mx
            ),
            Country(
                id = "indonesia",
                name = "Indonesia",
                timeZone = TimeZone.of("Asia/Jakarta"),
                image = Res.drawable.id
            ),
            Country(
                id = "egypt",
                name = "Egypt",
                timeZone = TimeZone.of("Africa/Cairo"),
                image = Res.drawable.eg
            )
        )
    }
}
