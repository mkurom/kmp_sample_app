package com.my.composedemo.shared.presentation.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.my.composedemo.shared.presentation.viewmodel.HomeViewModel

/**
 * ナビゲーション設定
 */
sealed class NavigationConfig {
    object Home : NavigationConfig()
    data class CountryDetail(val countryId: String) : NavigationConfig()
    
    companion object {
        val serializer = NavigationConfigSerializer
    }
}

/**
 * RootComponent - Decomposeを使用したナビゲーション管理
 */
class RootComponent(
    componentContext: ComponentContext,
    private val homeViewModel: HomeViewModel
) : ComponentContext by componentContext {
    
    private val navigation = StackNavigation<NavigationConfig>()
    
    val stack: Value<ChildStack<NavigationConfig, ChildComponent>> = childStack(
        source = navigation,
        serializer = NavigationConfigSerializer,
        initialConfiguration = NavigationConfig.Home,
        handleBackButton = true,
        childFactory = { config, componentContext ->
            when (config) {
                is NavigationConfig.Home -> ChildComponent.HomeComponent(
                    componentContext = componentContext,
                    homeViewModel = homeViewModel,
                    onNavigateToDetail = { countryId ->
                        navigation.push(NavigationConfig.CountryDetail(countryId))
                    }
                )
                is NavigationConfig.CountryDetail -> ChildComponent.CountryDetailComponent(
                    componentContext = componentContext,
                    countryId = config.countryId,
                    onBack = {
                        navigation.pop()
                    }
                )
            }
        }
    )
}

/**
 * 子コンポーネント
 */
sealed class ChildComponent {
    data class HomeComponent(
        val componentContext: ComponentContext,
        val homeViewModel: HomeViewModel,
        val onNavigateToDetail: (String) -> Unit
    ) : ChildComponent()
    
    data class CountryDetailComponent(
        val componentContext: ComponentContext,
        val countryId: String,
        val onBack: () -> Unit
    ) : ChildComponent()
}

