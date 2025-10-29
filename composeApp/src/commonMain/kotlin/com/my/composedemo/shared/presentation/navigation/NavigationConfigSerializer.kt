package com.my.composedemo.shared.presentation.navigation

import kotlinx.serialization.Serializable
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * NavigationConfigのシリアライザー
 */
object NavigationConfigSerializer : KSerializer<NavigationConfig> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("NavigationConfig")
    
    override fun serialize(encoder: Encoder, value: NavigationConfig) {
        when (value) {
            is NavigationConfig.Home -> encoder.encodeString("home")
            is NavigationConfig.CountryDetail -> encoder.encodeString("detail:${value.countryId}")
        }
    }
    
    override fun deserialize(decoder: Decoder): NavigationConfig {
        val string = decoder.decodeString()
        return when {
            string == "home" -> NavigationConfig.Home
            string.startsWith("detail:") -> {
                val countryId = string.removePrefix("detail:")
                NavigationConfig.CountryDetail(countryId)
            }
            else -> NavigationConfig.Home
        }
    }
}

