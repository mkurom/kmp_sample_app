package com.my.composedemo

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// ナビゲーションのタブ定義（iOSのSF Symbolsに似た絵文字）
enum class TabItem(val title: String, val icon: String) {
    HOME("Home", "🏠"), // house.fill
    SEARCH("Search", "🔍"), // magnifyingglass
    PROFILE("Profile", "👤"), // person.fill
    SETTINGS("Settings", "⚙️") // gear
}

// 共通ナビゲーション状態管理
class NavigationState {
    private val _selectedTab = MutableStateFlow(TabItem.HOME)
    val selectedTab: StateFlow<TabItem> = _selectedTab.asStateFlow()
    
    fun selectTab(tab: TabItem) {
        _selectedTab.value = tab
    }
    
    fun getCurrentTab(): TabItem = _selectedTab.value
}

// ナビゲーション用のコールバック定義
interface NavigationCallbacks {
    fun onNavigateToNativeTabBar()
    fun onNavigateToSwiftUITabBar()
}


