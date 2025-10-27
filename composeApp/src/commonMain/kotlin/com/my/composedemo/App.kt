package com.my.composedemo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.my.composedemo.presentation.navigation.BottomNavigationBar
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.my.composedemo.presentation.ui.screen.*

// メインのApp関数
@Composable
@Preview
fun App() {
    MaterialTheme {
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
                    .padding(bottom = innerPadding.calculateBottomPadding() - 16.dp)
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
