package com.my.composedemo.shared

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.my.composedemo.shared.presentation.ui.screen.HomeScreen
import com.my.composedemo.shared.presentation.ui.screen.SearchScreen
import com.my.composedemo.shared.presentation.ui.screen.ProfileScreen
import com.my.composedemo.shared.presentation.ui.screen.SettingsScreen

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

