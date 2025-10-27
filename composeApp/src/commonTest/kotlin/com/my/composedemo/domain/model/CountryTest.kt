package com.my.composedemo.domain.model

import com.my.composedemo.shared.domain.model.Country

import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Countryドメインモデルのユニットテスト
 * データクラスとファクトリメソッドの動作をテスト
 */
class CountryTest {

    @Test
    fun `createDefaultCountries should return correct number of countries`() {
        // When
        val countries = Country.createDefaultCountries()

        // Then
        assertEquals(5, countries.size)
    }

    @Test
    fun `createDefaultCountries should include Japan`() {
        // When
        val countries = Country.createDefaultCountries()
        val japan = countries.find { it.id == "japan" }

        // Then
        assertNotNull(japan)
        assertEquals("Japan", japan.name)
        assertEquals("Asia/Tokyo", japan.timeZone.id)
        assertFalse(japan.isFavorite)
    }

    @Test
    fun `createDefaultCountries should include France`() {
        // When
        val countries = Country.createDefaultCountries()
        val france = countries.find { it.id == "france" }

        // Then
        assertNotNull(france)
        assertEquals("France", france.name)
        assertEquals("Europe/Paris", france.timeZone.id)
        assertFalse(france.isFavorite)
    }

    @Test
    fun `createDefaultCountries should include Mexico`() {
        // When
        val countries = Country.createDefaultCountries()
        val mexico = countries.find { it.id == "mexico" }

        // Then
        assertNotNull(mexico)
        assertEquals("Mexico", mexico.name)
        assertEquals("America/Mexico_City", mexico.timeZone.id)
        assertFalse(mexico.isFavorite)
    }

    @Test
    fun `createDefaultCountries should include Indonesia`() {
        // When
        val countries = Country.createDefaultCountries()
        val indonesia = countries.find { it.id == "indonesia" }

        // Then
        assertNotNull(indonesia)
        assertEquals("Indonesia", indonesia.name)
        assertEquals("Asia/Jakarta", indonesia.timeZone.id)
        assertFalse(indonesia.isFavorite)
    }

    @Test
    fun `createDefaultCountries should include Egypt`() {
        // When
        val countries = Country.createDefaultCountries()
        val egypt = countries.find { it.id == "egypt" }

        // Then
        assertNotNull(egypt)
        assertEquals("Egypt", egypt.name)
        assertEquals("Africa/Cairo", egypt.timeZone.id)
        assertFalse(egypt.isFavorite)
    }

    @Test
    fun `createDefaultCountries should have unique IDs`() {
        // When
        val countries = Country.createDefaultCountries()
        val ids = countries.map { it.id }

        // Then
        assertEquals(ids.size, ids.distinct().size)
    }

    @Test
    fun `createDefaultCountries should have unique names`() {
        // When
        val countries = Country.createDefaultCountries()
        val names = countries.map { it.name }

        // Then
        assertEquals(names.size, names.distinct().size)
    }

    @Test
    fun `createDefaultCountries should have valid timezones`() {
        // When
        val countries = Country.createDefaultCountries()

        // Then
        countries.forEach { country ->
            assertTrue(country.timeZone.id.isNotEmpty())
            // Verify timezone is valid by checking it doesn't throw exception
            assertNotNull(TimeZone.of(country.timeZone.id))
        }
    }

    @Test
    fun `Country copy should work correctly`() {
        // Given
        val originalCountry = Country.createDefaultCountries().first()

        // When
        val copiedCountry = originalCountry.copy(
            name = "Updated Country",
            isFavorite = true
        )

        // Then
        assertEquals(originalCountry.id, copiedCountry.id)
        assertEquals("Updated Country", copiedCountry.name)
        assertEquals(originalCountry.timeZone, copiedCountry.timeZone)
        assertTrue(copiedCountry.isFavorite)
    }

    @Test
    fun `Country should have correct default values`() {
        // Given
        val country = Country.createDefaultCountries().first()

        // Then
        assertTrue(country.id.isNotEmpty())
        assertTrue(country.name.isNotEmpty())
        assertFalse(country.isFavorite) // Default value should be false
    }
}
