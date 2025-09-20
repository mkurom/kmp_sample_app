package com.my.composedemo

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

// Android用のBottomNavigation
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AndroidBottomNavigation(
    navigationState: NavigationState,
    modifier: Modifier = Modifier
) {
    val selectedTab by navigationState.selectedTab.collectAsState()
    
    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = modifier,
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                TabItem.values().forEach { tab ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = tab.getAndroidIcon(),
                                contentDescription = tab.title
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
            NavigationContent(
                navigationState = navigationState
            )
        }
    }
}

// TabItemのAndroidアイコン拡張
fun TabItem.getAndroidIcon(): ImageVector {
    return when (this) {
        TabItem.HOME -> Icons.Default.Home
        TabItem.SEARCH -> Icons.Default.Search
        TabItem.PROFILE -> Icons.Default.Person
        TabItem.SETTINGS -> Icons.Default.Settings
    }
}

// Android用のメイン画面
@Composable
fun AndroidMainScreen(
    navigationState: NavigationState,
    onNavigateToNative: (() -> Unit)? = null
) {
    AndroidBottomNavigation(
        navigationState = navigationState
    )
}
