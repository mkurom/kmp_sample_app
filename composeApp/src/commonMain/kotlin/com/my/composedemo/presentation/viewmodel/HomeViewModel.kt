package com.my.composedemo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.composedemo.domain.model.Country
import com.my.composedemo.domain.model.TabItemIcon
import com.my.composedemo.domain.model.User
import com.my.composedemo.domain.model.Theme
import com.my.composedemo.domain.repository.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * ホーム画面のViewModel
 * ユースケースの役割も兼ねる（フロントエンド向けのシンプルなアーキテクチャ）
 */
class HomeViewModel(
    private val countryRepository: CountryRepository,
    private val appViewModel: AppViewModel
) : ViewModel() {
    
    // UI状態を管理
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    // ナビゲーション状態
    private val _selectedTab = MutableStateFlow(TabItemIcon.HOME)
    val selectedTab: StateFlow<TabItemIcon> = _selectedTab.asStateFlow()
    
    init {
        loadCountries()
    }
    
    /**
     * 国情報を読み込み（ユースケースの役割）
     */
    private fun loadCountries() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                
                countryRepository.getCountries().collect { countries ->
                    _uiState.value = _uiState.value.copy(
                        countries = countries,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }
    
    /**
     * タブを選択
     */
    fun selectTab(tab: TabItemIcon) {
        _selectedTab.value = tab
    }
    
    /**
     * お気に入りを切り替え（ユースケースの役割）
     */
    fun toggleFavorite(countryId: String) {
        viewModelScope.launch {
            try {
                countryRepository.toggleFavorite(countryId)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "Failed to toggle favorite"
                )
            }
        }
    }
    
    /**
     * 現在時刻を取得（ユースケースの役割）
     */
    fun getCurrentTime(country: Country): String {
        return try {
            val time = Clock.System.now()
            val localTime = time.toLocalDateTime(country.timeZone).time
            "The time in ${country.name} is ${localTime.hour}:${localTime.minute}:${localTime.second}"
        } catch (e: Exception) {
            "Error getting time"
        }
    }
    
    /**
     * エラーをクリア
     */
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
    
    /**
     * グローバルなユーザー情報を取得
     */
    fun getCurrentUser(): StateFlow<User?> = appViewModel.user
    
    /**
     * グローバルなテーマを取得
     */
    fun getCurrentTheme(): StateFlow<Theme> = appViewModel.theme
}

/**
 * ホーム画面のUI状態
 */
data class HomeUiState(
    val countries: List<Country> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
