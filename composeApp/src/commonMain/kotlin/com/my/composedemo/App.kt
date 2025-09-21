package com.my.composedemo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

// メインのApp関数
@Composable
@Preview
fun App() {
    val navigationState = remember { NavigationState() }
    
    MaterialTheme {
        PlatformNavigation(
            navigationState = navigationState,
            onNavigateToNative = null,
            onNavigateToSwiftUI = null
        )
    }
}

// iOS用のタブ画面表示関数
@Composable
fun AppForTab(screenType: String) {
    val kotlinScreenType = when (screenType) {
        "home" -> ComposeScreenType.HOME
        "search" -> ComposeScreenType.SEARCH
        "profile" -> ComposeScreenType.PROFILE
        "settings" -> ComposeScreenType.SETTINGS
        else -> ComposeScreenType.HOME
    }
    
    MaterialTheme {
        ScreenContent(screenType = kotlinScreenType)
    }
}
