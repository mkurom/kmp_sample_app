package com.my.composedemo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.composedemo.domain.model.User
import com.my.composedemo.domain.model.Theme
import com.my.composedemo.domain.model.TabItemIcon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * アプリ全体で共有されるグローバルViewModel
 * ユーザー情報、認証状態、テーマ設定などを管理
 */
class AppViewModel : ViewModel() {
    
    // ユーザー情報
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()
    
    // 認証状態
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()
    
    // テーマ設定
    private val _theme = MutableStateFlow(Theme.SYSTEM)
    val theme: StateFlow<Theme> = _theme.asStateFlow()
    
    // アプリの初期化状態
    private val _isInitialized = MutableStateFlow(false)
    val isInitialized: StateFlow<Boolean> = _isInitialized.asStateFlow()
    
    // エラー状態
    private val _globalError = MutableStateFlow<String?>(null)
    val globalError: StateFlow<String?> = _globalError.asStateFlow()
    
    /**
     * ユーザー情報を設定
     */
    fun setUser(user: User?) {
        _user.value = user
        _isAuthenticated.value = user != null
    }
    
    /**
     * テーマを変更
     */
    fun setTheme(theme: Theme) {
        _theme.value = theme
    }
    
    /**
     * アプリの初期化完了
     */
    fun setInitialized() {
        _isInitialized.value = true
    }
    
    /**
     * グローバルエラーを設定
     */
    fun setGlobalError(error: String?) {
        _globalError.value = error
    }
    
    /**
     * エラーをクリア
     */
    fun clearError() {
        _globalError.value = null
    }
}
