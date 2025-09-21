package com.my.composedemo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

@Composable
actual fun PlatformNavigation(
    navigationState: NavigationState,
    onNavigateToNative: (() -> Unit)?,
    onNavigateToSwiftUI: (() -> Unit)?
) {
    val selectedTab by navigationState.selectedTab.collectAsState()
    
    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier,
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 3.dp
            ) {
                TabItem.values().forEach { tab ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = getMaterialIcon(tab),
                                contentDescription = tab.title,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = { Text(tab.title) },
                        selected = selectedTab == tab,
                        onClick = { navigationState.selectTab(tab) },
                        colors = NavigationBarItemColors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            selectedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                            disabledIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                            disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 各タブのコンテンツを表示
            when (selectedTab) {
                TabItem.HOME -> ScreenContent(screenType = ComposeScreenType.HOME)
                TabItem.SEARCH -> ScreenContent(screenType = ComposeScreenType.SEARCH)
                TabItem.PROFILE -> ScreenContent(screenType = ComposeScreenType.PROFILE)
                TabItem.SETTINGS -> ScreenContent(screenType = ComposeScreenType.SETTINGS)
            }
        }
    }
}

// Material Design準拠のアイコン取得関数
@Composable
private fun getMaterialIcon(tab: TabItem): ImageVector {
    return when (tab) {
        TabItem.HOME -> Icons.Filled.Home
        TabItem.SEARCH -> Icons.Filled.Search
        TabItem.PROFILE -> Icons.Filled.Person
        TabItem.SETTINGS -> Icons.Filled.Settings
    }
}
