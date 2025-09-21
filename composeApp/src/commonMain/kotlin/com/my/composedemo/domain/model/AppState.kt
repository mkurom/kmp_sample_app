package com.my.composedemo.domain.model

/**
 * アプリケーションの状態を表現するエンティティ
 */
enum class AppState {
    SPLASH,
    HOME,
    LOADING,
    ERROR
}

/**
 * ナビゲーションのタブを表現するエンティティ
 */
enum class TabItem(val title: String, val icon: String) {
    HOME("Home", "🏠"),
    SEARCH("Search", "🔍"),
    PROFILE("Profile", "👤"),
    SETTINGS("Settings", "⚙️")
}

/**
 * ユーザー情報を表現するエンティティ
 */
data class User(
    val id: String,
    val name: String,
    val email: String,
    val avatar: String? = null,
    val preferences: UserPreferences = UserPreferences()
)

/**
 * ユーザーの設定情報
 */
data class UserPreferences(
    val theme: Theme = Theme.SYSTEM,
    val language: String = "en",
    val notifications: Boolean = true
)

enum class Theme {
    LIGHT, DARK, SYSTEM
}
