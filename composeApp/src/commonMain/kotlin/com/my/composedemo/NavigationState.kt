package com.my.composedemo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// ナビゲーションのタブ定義
enum class TabItem(val title: String, val icon: String) {
    HOME("Home", "🏠"),
    SEARCH("Search", "🔍"),
    PROFILE("Profile", "👤"),
    SETTINGS("Settings", "⚙️")
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

// 共通のナビゲーションコンテンツ
@Composable
fun NavigationContent(
    navigationState: NavigationState,
    onNavigateToNative: (() -> Unit)? = null,
    onNavigateToSwiftUI: (() -> Unit)? = null
) {
    val selectedTab by navigationState.selectedTab.collectAsState()
    
    when (selectedTab) {
        TabItem.HOME -> HomeScreen(
            onNavigateToNative = onNavigateToNative,
            onNavigateToSwiftUI = onNavigateToSwiftUI
        )
        TabItem.SEARCH -> SearchScreen()
        TabItem.PROFILE -> ProfileScreen()
        TabItem.SETTINGS -> SettingsScreen()
    }
}

// 各画面のコンテンツ
@Composable
fun HomeScreen(
    onNavigateToNative: (() -> Unit)? = null,
    onNavigateToSwiftUI: (() -> Unit)? = null
) {
    androidx.compose.foundation.layout.Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        androidx.compose.material3.Text(
            text = "Home Screen",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
        )
        androidx.compose.material3.Text(
            text = "Welcome to the home screen!",
            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
            modifier = androidx.compose.ui.Modifier.padding(top = 8.dp)
        )
        
        androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(24.dp))
        
        // ナビゲーションボタン
        onNavigateToSwiftUI?.let { navigate ->
            androidx.compose.material3.Button(
                onClick = navigate,
                modifier = androidx.compose.ui.Modifier.padding(8.dp)
            ) {
                androidx.compose.material3.Text("Open SwiftUI Screen")
            }
        }
        
        onNavigateToNative?.let { navigate ->
            androidx.compose.material3.Button(
                onClick = navigate,
                modifier = androidx.compose.ui.Modifier.padding(8.dp)
            ) {
                androidx.compose.material3.Text("Open Native Screen")
            }
        }
    }
}

@Composable
fun SearchScreen() {
    androidx.compose.foundation.layout.Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        androidx.compose.material3.Text(
            text = "Search Screen",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
        )
        androidx.compose.material3.Text(
            text = "Search for content here!",
            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
            modifier = androidx.compose.ui.Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun ProfileScreen() {
    androidx.compose.foundation.layout.Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        androidx.compose.material3.Text(
            text = "Profile Screen",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
        )
        androidx.compose.material3.Text(
            text = "Manage your profile here!",
            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
            modifier = androidx.compose.ui.Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun SettingsScreen() {
    androidx.compose.foundation.layout.Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        androidx.compose.material3.Text(
            text = "Settings Screen",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
        )
        androidx.compose.material3.Text(
            text = "Configure your settings!",
            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
            modifier = androidx.compose.ui.Modifier.padding(top = 8.dp)
        )
    }
}
