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
    
    /**
     * Serializes a NavigationConfig to a compact string form for encoding.
     *
     * Produces "home" for NavigationConfig.Home and "detail:<countryId>" for NavigationConfig.CountryDetail.
     *
     * @param value The NavigationConfig instance to serialize.
     */
    override fun serialize(encoder: Encoder, value: NavigationConfig) {
        when (value) {
            is NavigationConfig.Home -> encoder.encodeString("home")
            is NavigationConfig.CountryDetail -> encoder.encodeString("detail:${value.countryId}")
        }
    }
    
    /**
     * Deserializes a string-encoded NavigationConfig from the provided decoder.
     *
     * @param decoder The decoder to read the string representation from.
     * @return `NavigationConfig.Home` when the decoded string is `"home"` or when the value is unrecognized; `NavigationConfig.CountryDetail(countryId)` when the decoded string has the form `"detail:<countryId>"`.
     */
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
