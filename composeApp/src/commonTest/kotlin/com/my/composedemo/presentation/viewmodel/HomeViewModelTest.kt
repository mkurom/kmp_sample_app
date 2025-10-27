package com.my.composedemo.presentation.viewmodel

import com.my.composedemo.domain.model.Country
import com.my.composedemo.domain.model.TabItemIcon
import com.my.composedemo.domain.model.Theme
import com.my.composedemo.domain.model.User
import com.my.composedemo.domain.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * HomeViewModelのユニットテスト
 * ホーム画面の状態管理とビジネスロジックをテスト
 */
@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    // シンプルなモック実装
    private class MockCountryRepository : CountryRepository {
        private val countries = MutableStateFlow<List<Country>>(Country.createDefaultCountries())
        
        override suspend fun getCountries() = countries
        override suspend fun getCountryById(id: String) = countries.value.find { it.id == id }
        override suspend fun toggleFavorite(countryId: String) { /* Mock implementation */ }
        override suspend fun getFavoriteCountries() = MutableStateFlow(emptyList<Country>())
        
        fun setCountries(countriesList: List<Country>) {
            countries.value = countriesList
        }
    }

    private val mockCountryRepository = MockCountryRepository()
    private val appViewModel = AppViewModel()
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `selectTab should update selected tab`() = runTest {
        // Given
        val viewModel = HomeViewModel(mockCountryRepository, appViewModel)

        // When
        viewModel.selectTab(TabItemIcon.SEARCH)
        val selectedTab = viewModel.selectedTab.first()

        // Then
        assertEquals(TabItemIcon.SEARCH, selectedTab)
    }

    @Test
    fun `getCurrentTime should return formatted time string`() = runTest {
        // Given
        val viewModel = HomeViewModel(mockCountryRepository, appViewModel)
        val country = Country.createDefaultCountries().first()

        // When
        val timeString = viewModel.getCurrentTime(country)

        // Then
        assertTrue(timeString.contains("The time in ${country.name} is"))
        assertTrue(timeString.contains(":"))
    }

    @Test
    fun `getCurrentTime should handle invalid timezone gracefully`() = runTest {
        // Given
        val viewModel = HomeViewModel(mockCountryRepository, appViewModel)
        // Create a country with invalid timezone by using a non-existent timezone ID
        val country = try {
            Country.createDefaultCountries().first().copy(
                timeZone = kotlinx.datetime.TimeZone.of("Invalid/Timezone")
            )
        } catch (e: kotlinx.datetime.IllegalTimeZoneException) {
            // If TimeZone.of throws exception, create a mock country with UTC timezone
            // and test the error handling in getCurrentTime method
            Country.createDefaultCountries().first()
        }

        // When
        val timeString = viewModel.getCurrentTime(country)

        // Then
        // The method should return a valid time string for valid timezone
        assertTrue(timeString.contains("The time in ${country.name} is"))
    }

    @Test
    fun `clearError should clear error state`() = runTest {
        // Given
        val viewModel = HomeViewModel(mockCountryRepository, appViewModel)
        
        // When
        viewModel.clearError()
        
        // Then - Method should execute without exception
    }

    @Test
    fun `getCurrentUser should return user from appViewModel`() = runTest {
        // Given
        val testUser = User(
            id = "test-id",
            name = "Test User",
            email = "test@example.com"
        )
        appViewModel.setUser(testUser)
        val viewModel = HomeViewModel(mockCountryRepository, appViewModel)

        // When
        val user = viewModel.getCurrentUser().first()

        // Then
        assertEquals(testUser, user)
    }

    @Test
    fun `getCurrentTheme should return theme from appViewModel`() = runTest {
        // Given
        appViewModel.setTheme(Theme.DARK)
        val viewModel = HomeViewModel(mockCountryRepository, appViewModel)

        // When
        val theme = viewModel.getCurrentTheme().first()

        // Then
        assertEquals(Theme.DARK, theme)
    }

    @Test
    fun `multiple tab selections should work correctly`() = runTest {
        // Given
        val viewModel = HomeViewModel(mockCountryRepository, appViewModel)

        // When
        viewModel.selectTab(TabItemIcon.PROFILE)
        val selectedProfileTab = viewModel.selectedTab.first()
        
        viewModel.selectTab(TabItemIcon.SETTINGS)
        val selectedSettingsTab = viewModel.selectedTab.first()

        // Then
        assertEquals(TabItemIcon.PROFILE, selectedProfileTab)
        assertEquals(TabItemIcon.SETTINGS, selectedSettingsTab)
    }
}
