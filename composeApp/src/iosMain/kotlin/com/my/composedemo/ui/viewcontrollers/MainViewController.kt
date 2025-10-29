package com.my.composedemo.ui.viewcontrollers

import androidx.compose.ui.window.ComposeUIViewController
import com.my.composedemo.shared.App
import com.my.composedemo.shared.di.appModule
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

import org.koin.dsl.module

fun MainViewController(mapViewController: UIViewController): UIViewController =
    ComposeUIViewController {
        // Koinを初期化
        startKoin {
            modules(appModule)
            modules(module {
                single { mapViewController }
            })
        }
        
        App()
    }