package com.my.composedemo

actual class EnvironmentConfig {
    actual val apiBaseUrl: String
        get() = Platform.isDebugBinary.let { isDebug ->
            when {
                isDebug -> "https://dev-api.example.com"
                else -> "https://api.example.com"
            }
        }
    
    actual val environmentName: String
        get() = Platform.isDebugBinary.let { isDebug ->
            if (isDebug) "Development" else "Production"
        }
    
    actual val isDebug: Boolean
        get() = Platform.isDebugBinary
    
    actual val applicationId: String
        get() = "com.my.composedemo.ComposeDemo"
}
