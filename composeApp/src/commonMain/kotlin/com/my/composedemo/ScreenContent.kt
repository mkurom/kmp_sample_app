package com.my.composedemo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.my.composedemo.presentation.ui.screen.HomeScreen
import com.my.composedemo.presentation.ui.screen.SearchScreen
import com.my.composedemo.presentation.ui.screen.ProfileScreen
import com.my.composedemo.presentation.ui.screen.SettingsScreen

enum class ComposeScreenType {
    HOME,
    SEARCH,
    PROFILE,
    SETTINGS
}

@Composable
fun ScreenContent(screenType: ComposeScreenType) {
    MaterialTheme {
        when (screenType) {
            ComposeScreenType.HOME -> HomeScreen()
            ComposeScreenType.SEARCH -> SearchScreen()
            ComposeScreenType.PROFILE -> ProfileScreen()
            ComposeScreenType.SETTINGS -> SettingsScreen()
        }
    }
}
