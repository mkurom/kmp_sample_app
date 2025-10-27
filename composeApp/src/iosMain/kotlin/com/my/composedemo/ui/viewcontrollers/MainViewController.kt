package com.my.composedemo.ui.viewcontrollers

import androidx.compose.ui.window.ComposeUIViewController
import com.my.composedemo.App
import com.my.composedemo.di.appModule
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController =
    ComposeUIViewController {
        // Koinを初期化
        startKoin {
            modules(appModule)
        }
        
        App()
    }