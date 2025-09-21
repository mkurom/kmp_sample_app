package com.my.composedemo.presentation.ui.home.types

import com.my.composedemo.domain.model.Country

/**
 * ホーム画面固有の型定義
 */

/**
 * ホーム画面の状態
 */
data class HomeScreenState(
    val countries: List<Country> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val selectedCountry: Country? = null
)

/**
 * ホーム画面のアクション
 */
sealed class HomeScreenAction {
    data class ToggleFavorite(val countryId: String) : HomeScreenAction()
    data class Search(val query: String) : HomeScreenAction()
    data class SelectCountry(val country: Country) : HomeScreenAction()
    object ClearError : HomeScreenAction()
    object LoadCountries : HomeScreenAction()
}

/**
 * ホーム画面のイベント
 */
sealed class HomeScreenEvent {
    data class NavigateToCountryDetail(val country: Country) : HomeScreenEvent()
    data class ShowError(val message: String) : HomeScreenEvent()
    object NavigateToSearch : HomeScreenEvent()
}
