package com.my.composedemo.data.datasource

import com.my.composedemo.shared.domain.model.Country
import com.my.composedemo.shared.data.datasource.InMemoryCountryDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * InMemoryCountryDataSourceのユニットテスト
 * データソース層のビジネスロジックをテスト
 */
class InMemoryCountryDataSourceTest {

    private val dataSource = InMemoryCountryDataSource()

    @Test
    fun `getCountries should return default countries`() = runTest {
        // When
        val countries = dataSource.getCountries().first()

        // Then
        assertTrue(countries.isNotEmpty())
        assertEquals(5, countries.size)
        
        val japan = countries.find { it.id == "japan" }
        assertNotNull(japan)
        assertEquals("Japan", japan.name)
        assertFalse(japan.isFavorite)
    }

    @Test
    fun `getCountryById should return correct country`() = runTest {
        // When
        val country = dataSource.getCountryById("japan")

        // Then
        assertNotNull(country)
        assertEquals("japan", country.id)
        assertEquals("Japan", country.name)
    }

    @Test
    fun `getCountryById should return null for non-existent country`() = runTest {
        // When
        val country = dataSource.getCountryById("non-existent")

        // Then
        assertNull(country)
    }

    @Test
    fun `toggleFavorite should add country to favorites when not favorite`() = runTest {
        // Given
        val countryId = "japan"
        
        // When
        dataSource.toggleFavorite(countryId)
        val favorites = dataSource.getFavoriteCountries().first()
        val country = dataSource.getCountryById(countryId)

        // Then
        assertTrue(favorites.any { it.id == countryId })
        assertNotNull(country)
        assertTrue(country?.isFavorite ?: false)
    }

    @Test
    fun `toggleFavorite should remove country from favorites when already favorite`() = runTest {
        // Given
        val countryId = "japan"
        dataSource.toggleFavorite(countryId) // First toggle to add
        
        // When
        dataSource.toggleFavorite(countryId) // Second toggle to remove
        val favorites = dataSource.getFavoriteCountries().first()
        val country = dataSource.getCountryById(countryId)

        // Then
        assertFalse(favorites.any { it.id == countryId })
        assertNotNull(country)
        assertFalse(country?.isFavorite ?: false)
    }

    @Test
    fun `getFavoriteCountries should return only favorite countries`() = runTest {
        // Given
        dataSource.toggleFavorite("japan")
        dataSource.toggleFavorite("france")
        
        // When
        val favorites = dataSource.getFavoriteCountries().first()

        // Then
        assertEquals(2, favorites.size)
        assertTrue(favorites.any { it.id == "japan" })
        assertTrue(favorites.any { it.id == "france" })
        assertFalse(favorites.any { it.id == "mexico" })
    }

    @Test
    fun `toggleFavorite should work with multiple countries independently`() = runTest {
        // Given
        val japanId = "japan"
        val franceId = "france"
        
        // When
        dataSource.toggleFavorite(japanId)
        dataSource.toggleFavorite(franceId)
        dataSource.toggleFavorite(japanId) // Remove japan, keep france
        
        val favorites = dataSource.getFavoriteCountries().first()
        val japan = dataSource.getCountryById(japanId)
        val france = dataSource.getCountryById(franceId)

        // Then
        assertEquals(1, favorites.size)
        assertTrue(favorites.any { it.id == franceId })
        assertFalse(favorites.any { it.id == japanId })
        assertFalse(japan?.isFavorite ?: false)
        assertTrue(france?.isFavorite ?: false)
    }

    @Test
    fun `toggleFavorite should handle non-existent country gracefully`() = runTest {
        // Given
        val nonExistentId = "non-existent"
        
        // When & Then - Should not throw exception
        dataSource.toggleFavorite(nonExistentId)
        
        val favorites = dataSource.getFavoriteCountries().first()
        assertTrue(favorites.isEmpty())
    }
}
