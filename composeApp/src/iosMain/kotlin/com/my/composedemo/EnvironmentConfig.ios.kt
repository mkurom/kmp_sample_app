package com.my.composedemo

actual class EnvironmentConfig {
    actual val apiBaseUrl: String
        get() = "https://api.example.com"
    
    actual val environmentName: String
        get() = "Production"
    
    actual val isDebug: Boolean
        get() = false
    
    actual val applicationId: String
        get() = "com.my.composedemo.ComposeDemo"
}
