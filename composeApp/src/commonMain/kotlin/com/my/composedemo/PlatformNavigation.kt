package com.my.composedemo

import androidx.compose.runtime.Composable
import com.my.composedemo.NavigationState

@Composable
expect fun PlatformNavigation(
    navigationState: NavigationState,
    onNavigateToNative: (() -> Unit)?,
    onNavigateToSwiftUI: (() -> Unit)?
)
