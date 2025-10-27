package com.my.composedemo

import kotlinx.cinterop.*
import platform.Foundation.*
import platform.posix.getenv

@OptIn(ExperimentalForeignApi::class)
private fun getGoogleMapsApiKey(): String {
    // First try to get from environment variable
    val envKey = getenv("GOOGLE_MAPS_API_KEY")?.toKString()
    if (envKey != null) {
        return envKey
    }
    
    // Fallback to Info.plist
    val bundle = NSBundle.mainBundle
    val apiKey = bundle.objectForInfoDictionaryKey("GMSApiKey") as? String
    return apiKey ?: "YOUR_GOOGLE_MAPS_API_KEY_HERE"
}

actual class EnvironmentConfig {
    actual val apiBaseUrl: String
        get() = "https://api.example.com"
    
    actual val environmentName: String
        get() = "Production"
    
    actual val isDebug: Boolean
        get() = false
    
    actual val applicationId: String
        get() = "com.my.composedemo.ComposeDemo"
    
    actual val googleMapsApiKey: String
        get() = getGoogleMapsApiKey()
}
