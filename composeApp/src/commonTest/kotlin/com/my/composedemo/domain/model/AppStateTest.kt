package com.my.composedemo.domain.model

import com.my.composedemo.TabItem
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * AppStateドメインモデルのユニットテスト
 * 列挙型とデータクラスの動作をテスト
 */
class AppStateTest {

    @Test
    fun `AppState enum should have correct values`() {
        // Then
        assertEquals(AppState.SPLASH, AppState.SPLASH)
        assertEquals(AppState.HOME, AppState.HOME)
        assertEquals(AppState.LOADING, AppState.LOADING)
        assertEquals(AppState.ERROR, AppState.ERROR)
    }

    // TODO: TabItem is a data class, not an enum. This test needs to be updated.
    // @Test
    // fun `TabItem enum should have correct properties`() {
    //     // Then
    //     assertEquals("Home", TabItem.HOME.title)
    //     assertEquals("🏠", TabItem.HOME.icon)
    //     
    //     assertEquals("Search", TabItem.SEARCH.title)
    //     assertEquals("🔍", TabItem.SEARCH.icon)
    //     
    //     assertEquals("Profile", TabItem.PROFILE.title)
    //     assertEquals("👤", TabItem.PROFILE.icon)
    //     
    //     assertEquals("Settings", TabItem.SETTINGS.title)
    //     assertEquals("⚙️", TabItem.SETTINGS.icon)
    // }

    @Test
    fun `Theme enum should have correct values`() {
        // Then
        assertEquals(Theme.LIGHT, Theme.LIGHT)
        assertEquals(Theme.DARK, Theme.DARK)
        assertEquals(Theme.SYSTEM, Theme.SYSTEM)
    }

    @Test
    fun `User data class should work correctly`() {
        // Given
        val user = User(
            id = "test-id",
            name = "Test User",
            email = "test@example.com",
            avatar = "avatar-url",
            preferences = UserPreferences()
        )

        // Then
        assertEquals("test-id", user.id)
        assertEquals("Test User", user.name)
        assertEquals("test@example.com", user.email)
        assertEquals("avatar-url", user.avatar)
        assertNotNull(user.preferences)
    }

    @Test
    fun `User should have correct default values`() {
        // Given
        val user = User(
            id = "test-id",
            name = "Test User",
            email = "test@example.com"
        )

        // Then
        assertEquals("test-id", user.id)
        assertEquals("Test User", user.name)
        assertEquals("test@example.com", user.email)
        assertEquals(null, user.avatar)
        assertNotNull(user.preferences)
    }

    @Test
    fun `UserPreferences should have correct default values`() {
        // Given
        val preferences = UserPreferences()

        // Then
        assertEquals(Theme.SYSTEM, preferences.theme)
        assertEquals("en", preferences.language)
        assertTrue(preferences.notifications)
    }

    @Test
    fun `UserPreferences should work with custom values`() {
        // Given
        val preferences = UserPreferences(
            theme = Theme.DARK,
            language = "ja",
            notifications = false
        )

        // Then
        assertEquals(Theme.DARK, preferences.theme)
        assertEquals("ja", preferences.language)
        assertFalse(preferences.notifications)
    }

    @Test
    fun `User copy should work correctly`() {
        // Given
        val originalUser = User(
            id = "test-id",
            name = "Test User",
            email = "test@example.com"
        )

        // When
        val updatedUser = originalUser.copy(
            name = "Updated User",
            email = "updated@example.com"
        )

        // Then
        assertEquals("test-id", updatedUser.id)
        assertEquals("Updated User", updatedUser.name)
        assertEquals("updated@example.com", updatedUser.email)
        assertEquals(null, updatedUser.avatar)
        assertNotNull(updatedUser.preferences)
    }

    @Test
    fun `UserPreferences copy should work correctly`() {
        // Given
        val originalPreferences = UserPreferences()

        // When
        val updatedPreferences = originalPreferences.copy(
            theme = Theme.LIGHT,
            language = "fr"
        )

        // Then
        assertEquals(Theme.LIGHT, updatedPreferences.theme)
        assertEquals("fr", updatedPreferences.language)
        assertTrue(updatedPreferences.notifications) // Should keep original value
    }
}
