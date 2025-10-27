package com.my.composedemo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.my.composedemo.domain.model.Theme
import com.my.composedemo.presentation.navigation.BottomNavigationBar
import com.my.composedemo.presentation.viewmodel.AppViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.my.composedemo.presentation.ui.screen.*
import org.koin.compose.koinInject

// メインのApp関数
@Composable
@Preview
fun App() {
    val appViewModel: AppViewModel = koinInject()
    val theme by appViewModel.theme.collectAsState()
    
    val useDarkTheme = when (theme) {
        Theme.LIGHT -> false
        Theme.DARK -> true
        Theme.SYSTEM -> false // TODO: システム設定を検出
    }
    
    val colorScheme = if (useDarkTheme) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }
    
    MaterialTheme(
        colorScheme = colorScheme
    ) {
        val seletedIndex = remember { mutableStateOf(0) }
        
        val tabs = remember {
            listOf(
                TabItem("Home", AppIconHome(), ComposeScreenType.HOME),
                TabItem("Search", AppIconSearch(), ComposeScreenType.SEARCH),
                TabItem("Profile", AppIconProfile(), ComposeScreenType.PROFILE),
                TabItem("Settings", AppIconSettings(), ComposeScreenType.SETTINGS)
            )
        }
        
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    tabs = tabs,
                    selectedTab = tabs[seletedIndex.value],
                    onTabSelected = { tab ->
                        seletedIndex.value = tabs.indexOf(tab)
                    }
                )
            },
        ) { innerPadding ->
            
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = innerPadding.calculateBottomPadding())
            ) {
                when (seletedIndex.value) {
                    ComposeScreenType.HOME.ordinal -> HomeScreen()
                    ComposeScreenType.SEARCH.ordinal -> SearchScreen()
                    ComposeScreenType.PROFILE.ordinal -> ProfileScreen()
                    ComposeScreenType.SETTINGS.ordinal -> SettingsScreen()
                }
            }
        }
    }
}

data class TabItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector?,
    val screenType: ComposeScreenType
)
