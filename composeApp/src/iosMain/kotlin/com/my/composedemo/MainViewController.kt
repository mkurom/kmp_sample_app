package com.my.composedemo

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController(onNavigateToSwiftUI: (() -> Unit)? = null) = 
    ComposeUIViewController { 
        App(onNavigateToSwiftUI = onNavigateToSwiftUI) 
    }