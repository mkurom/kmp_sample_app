package com.my.composedemo.shared

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import com.my.composedemo.shared.domain.model.Theme
import com.my.composedemo.shared.presentation.viewmodel.AppViewModel
import com.my.composedemo.shared.presentation.viewmodel.HomeViewModel
import com.my.composedemo.shared.presentation.ui.screen.SearchScreen
import com.my.composedemo.shared.presentation.ui.screen.ProfileScreen
import com.my.composedemo.shared.presentation.ui.screen.SettingsScreen
import com.my.composedemo.shared.presentation.ui.screen.HomeScreen
import com.my.composedemo.shared.presentation.ui.screen.CountryDetailScreen
import com.my.composedemo.shared.presentation.navigation.BottomNavigationBar
import com.my.composedemo.shared.presentation.navigation.RootComponent
import com.my.composedemo.shared.presentation.navigation.ChildComponent
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.resume
import com.my.composedemo.platform.AppIconHome
import com.my.composedemo.platform.AppIconSearch
import com.my.composedemo.platform.AppIconProfile
import com.my.composedemo.platform.AppIconSettings
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

/**
 * Home画面のナビゲーション管理関数
 * Decomposeを使用してプラットフォーム共通で実装
 */
@Composable
fun HomeScreenWithNavigation() {
    val homeViewModel: HomeViewModel = koinInject()
    
    val rootComponent = remember {
        RootComponent(
            componentContext = DefaultComponentContext(lifecycle = LifecycleRegistry()),
            homeViewModel = homeViewModel
        )
    }

    DisposableEffect(Unit) {
        val lifecycle = rootComponent.lifecycle as LifecycleRegistry
        lifecycle.resume()
        onDispose {
            lifecycle.destroy()
        }
    }
    
    Children(
        stack = rootComponent.stack,
        modifier = Modifier.fillMaxSize()
    ) { child ->
        when (val component = child.instance) {
            is ChildComponent.HomeComponent -> {
                HomeScreen(
                    homeViewModel = component.homeViewModel,
                    onNavigateToDetail = component.onNavigateToDetail
                )
            }
            is ChildComponent.CountryDetailComponent -> {
                CountryDetailScreen(
                    countryId = component.countryId,
                    onBack = component.onBack
                )
            }
        }
    }
}

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
        val currentTab = remember { mutableStateOf(ComposeScreenType.HOME) }
        
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
                    selectedTab = tabs[currentTab.value.ordinal],
                    onTabSelected = { tab ->
                        currentTab.value = tab.screenType
                    }
                )
            }
        ) { innerPadding ->
            when (currentTab.value) {
                ComposeScreenType.HOME -> {
                    // Android側でNavigation Composeを使用してナビゲーションを管理
                    HomeScreenWithNavigation()
                }
                ComposeScreenType.SEARCH -> SearchScreen()
                ComposeScreenType.PROFILE -> ProfileScreen()
                ComposeScreenType.SETTINGS -> SettingsScreen()
            }
        }
    }
}

data class TabItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector?,
    val screenType: ComposeScreenType
)

enum class ComposeScreenType {
    HOME,
    SEARCH,
    PROFILE,
    SETTINGS
}