package com.my.composedemo

import androidx.compose.runtime.Composable

@Composable
actual fun PlatformNavigation(
    navigationState: NavigationState,
    onNavigateToNative: (() -> Unit)?,
    onNavigateToSwiftUI: (() -> Unit)?
) {
    // iOS用の共通ナビゲーションコンテンツを使用
    // SwiftUIのBottomNavigationはiOS側で処理
    NavigationContent(
        navigationState = navigationState,
        onNavigateToNative = onNavigateToNative,
        onNavigateToSwiftUI = onNavigateToSwiftUI
    )
}
