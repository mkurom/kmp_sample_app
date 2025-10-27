package com.my.composedemo.platform

// 共通の環境設定インターフェース
expect class EnvironmentConfig {
    val apiBaseUrl: String
    val environmentName: String
    val isDebug: Boolean
    val applicationId: String
    val googleMapsApiKey: String
}

