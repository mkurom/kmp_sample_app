package com.my.composedemo.ui.viewcontrollers

import androidx.compose.ui.window.ComposeUIViewController
import com.my.composedemo.ScreenContent
import com.my.composedemo.ComposeScreenType
import com.my.composedemo.di.appModule
import org.koin.core.context.startKoin

fun createComposeScreen(screenType: String) =
    ComposeUIViewController {
        // Koinを初期化（既に初期化されている場合はスキップ）
        try {
            startKoin {
                modules(appModule)
            }
        } catch (e: Exception) {
            // Koinが既に初期化されている場合は無視
        }
        
        // SwiftUIのenumをKotlinのenumにマッピング
        val kotlinScreenType = when (screenType) {
            "home" -> ComposeScreenType.HOME
            "search" -> ComposeScreenType.SEARCH
            "profile" -> ComposeScreenType.PROFILE
            "settings" -> ComposeScreenType.SETTINGS
            else -> ComposeScreenType.HOME
        }
        
        ScreenContent(screenType = kotlinScreenType)
    }