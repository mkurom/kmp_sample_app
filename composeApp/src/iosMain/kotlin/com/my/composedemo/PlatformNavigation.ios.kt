package com.my.composedemo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
actual fun PlatformNavigation(
    navigationState: NavigationState,
    onNavigateToNative: (() -> Unit)?,
    onNavigateToSwiftUI: (() -> Unit)?
) {
    // iOS側ではプレースホルダーを表示（実際のナビゲーションはSwiftUIで実装）
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(
            text = "iOS App (Placeholder)",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
