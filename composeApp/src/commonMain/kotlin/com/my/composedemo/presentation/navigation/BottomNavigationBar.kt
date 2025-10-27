package com.my.composedemo.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.my.composedemo.AppIconHomePainter
import com.my.composedemo.AppIconProfilePainter
import com.my.composedemo.AppIconSearchPainter
import com.my.composedemo.AppIconSettingsPainter
import com.my.composedemo.ComposeScreenType
import com.my.composedemo.TabItem

@Composable
fun BottomNavigationBar(
    tabs: List<TabItem>,
    selectedTab: TabItem,
    onTabSelected: (TabItem) -> Unit
) {
    NavigationBar {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                icon = {
                    BottomNavigationBarItemIcon(
                        tab = tab,
                        selectedTab = selectedTab
                    )
                },
                label = {
                    Text(tab.title)
                },
                selected = selectedTab.screenType == tab.screenType,
                onClick = {
                    onTabSelected(tab)
                }
            )
        }
    }
}

@Composable
fun BottomNavigationBarItemIcon(
    tab: TabItem,
    selectedTab: TabItem
) {
    val painter = when (tab.screenType) {
        ComposeScreenType.HOME -> AppIconHomePainter()
        ComposeScreenType.SEARCH -> AppIconSearchPainter()
        ComposeScreenType.PROFILE -> AppIconProfilePainter()
        ComposeScreenType.SETTINGS -> AppIconSettingsPainter()
    }
    
    when {
        painter != null -> {
            // SVG使用（iOS）
            val iconColor = if (selectedTab.screenType == tab.screenType) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
            
            Image(
                painter = painter,
                contentDescription = tab.title,
                modifier = Modifier.size(24.dp),
                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(iconColor)
            )
        }
        tab.icon != null -> {
            // Material Icons使用（Android）
            Icon(
                imageVector = tab.icon,
                contentDescription = tab.title
            )
        }
        else -> {
            Box(
                modifier = Modifier.size(12.dp)
            )
        }
    }
}