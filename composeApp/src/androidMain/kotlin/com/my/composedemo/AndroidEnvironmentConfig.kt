package com.my.composedemo

import com.my.composedemo.BuildConfig

actual class EnvironmentConfig {
    actual val apiBaseUrl: String
        get() = BuildConfig.API_BASE_URL
    
    actual val environmentName: String
        get() = BuildConfig.ENVIRONMENT_NAME
    
    actual val isDebug: Boolean
        get() = BuildConfig.DEBUG
    
    actual val applicationId: String
        get() = BuildConfig.APPLICATION_ID
    
    actual val googleMapsApiKey: String
        get() = BuildConfig.GOOGLE_MAPS_API_KEY
}
