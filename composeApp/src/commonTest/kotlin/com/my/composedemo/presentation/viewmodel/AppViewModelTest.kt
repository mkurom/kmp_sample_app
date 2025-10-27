package com.my.composedemo.presentation.viewmodel

import com.my.composedemo.shared.domain.model.Theme
import com.my.composedemo.shared.domain.model.User
import com.my.composedemo.shared.domain.model.UserPreferences
import com.my.composedemo.shared.presentation.viewmodel.AppViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * AppViewModelのユニットテスト
 * グローバル状態管理のロジックをテスト
 */
class AppViewModelTest {

    private val viewModel = AppViewModel()

    @Test
    fun `initial state should have correct default values`() = runTest {
        // When
        val user = viewModel.user.first()
        val isAuthenticated = viewModel.isAuthenticated.first()
        val theme = viewModel.theme.first()
        val isInitialized = viewModel.isInitialized.first()
        val globalError = viewModel.globalError.first()

        // Then
        assertNull(user)
        assertFalse(isAuthenticated)
        assertEquals(Theme.SYSTEM, theme)
        assertFalse(isInitialized)
        assertNull(globalError)
    }

    @Test
    fun `setUser should update user and authentication state`() = runTest {
        // Given
        val testUser = User(
            id = "test-id",
            name = "Test User",
            email = "test@example.com",
            preferences = UserPreferences()
        )

        // When
        viewModel.setUser(testUser)
        val user = viewModel.user.first()
        val isAuthenticated = viewModel.isAuthenticated.first()

        // Then
        assertEquals(testUser, user)
        assertTrue(isAuthenticated)
    }

    @Test
    fun `setUser with null should clear user and authentication`() = runTest {
        // Given
        val testUser = User(
            id = "test-id",
            name = "Test User",
            email = "test@example.com"
        )
        viewModel.setUser(testUser)

        // When
        viewModel.setUser(null)
        val user = viewModel.user.first()
        val isAuthenticated = viewModel.isAuthenticated.first()

        // Then
        assertNull(user)
        assertFalse(isAuthenticated)
    }

    @Test
    fun `setTheme should update theme state`() = runTest {
        // When
        viewModel.setTheme(Theme.DARK)
        val theme = viewModel.theme.first()

        // Then
        assertEquals(Theme.DARK, theme)
    }

    @Test
    fun `setTheme should work with all theme values`() = runTest {
        // Test LIGHT theme
        viewModel.setTheme(Theme.LIGHT)
        assertEquals(Theme.LIGHT, viewModel.theme.first())

        // Test DARK theme
        viewModel.setTheme(Theme.DARK)
        assertEquals(Theme.DARK, viewModel.theme.first())

        // Test SYSTEM theme
        viewModel.setTheme(Theme.SYSTEM)
        assertEquals(Theme.SYSTEM, viewModel.theme.first())
    }

    @Test
    fun `setInitialized should update initialization state`() = runTest {
        // When
        viewModel.setInitialized()
        val isInitialized = viewModel.isInitialized.first()

        // Then
        assertTrue(isInitialized)
    }

    @Test
    fun `setGlobalError should update error state`() = runTest {
        // Given
        val errorMessage = "Test error message"

        // When
        viewModel.setGlobalError(errorMessage)
        val globalError = viewModel.globalError.first()

        // Then
        assertEquals(errorMessage, globalError)
    }

    @Test
    fun `setGlobalError with null should clear error state`() = runTest {
        // Given
        viewModel.setGlobalError("Test error")

        // When
        viewModel.setGlobalError(null)
        val globalError = viewModel.globalError.first()

        // Then
        assertNull(globalError)
    }

    @Test
    fun `clearError should clear error state`() = runTest {
        // Given
        viewModel.setGlobalError("Test error")

        // When
        viewModel.clearError()
        val globalError = viewModel.globalError.first()

        // Then
        assertNull(globalError)
    }

    @Test
    fun `multiple state changes should work independently`() = runTest {
        // Given
        val testUser = User(
            id = "test-id",
            name = "Test User",
            email = "test@example.com"
        )

        // When
        viewModel.setUser(testUser)
        viewModel.setTheme(Theme.DARK)
        viewModel.setInitialized()
        viewModel.setGlobalError("Test error")

        val user = viewModel.user.first()
        val isAuthenticated = viewModel.isAuthenticated.first()
        val theme = viewModel.theme.first()
        val isInitialized = viewModel.isInitialized.first()
        val globalError = viewModel.globalError.first()

        // Then
        assertEquals(testUser, user)
        assertTrue(isAuthenticated)
        assertEquals(Theme.DARK, theme)
        assertTrue(isInitialized)
        assertEquals("Test error", globalError)
    }
}
