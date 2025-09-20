package com.my.composedemo

import androidx.compose.runtime.Composable

@Composable
actual fun PlatformNavigation(
    navigationState: NavigationState,
    onNavigateToNative: (() -> Unit)?,
    onNavigateToSwiftUI: (() -> Unit)?
) {
    // Android用のBottomNavigationを使用
    AndroidMainScreen(
        navigationState = navigationState,
        onNavigateToNative = onNavigateToNative
    )
}
