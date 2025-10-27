import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.androidApplication)  // 順序を変更: kotlinMultiplatformの後に適用
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation("com.google.android.gms:play-services-maps:18.2.0")
            implementation("com.google.maps.android:maps-compose:4.3.0")
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.2")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.turbine)
        }
    }
}

android {
    namespace = "com.my.composedemo"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.my.composedemo"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        
        // BuildConfigを有効化
        buildConfigField("String", "API_BASE_URL", "\"https://api.example.com\"")
        buildConfigField("String", "ENVIRONMENT_NAME", "\"Production\"")
        
        // Google Maps API Key from local.properties or environment variable
        val googleMapsApiKey = project.findProperty("GOOGLE_MAPS_API_KEY") as String? 
            ?: System.getenv("GOOGLE_MAPS_API_KEY") 
            ?: "YOUR_GOOGLE_MAPS_API_KEY_HERE"
        
        println("Google Maps API Key: $googleMapsApiKey")
        buildConfigField("String", "GOOGLE_MAPS_API_KEY", "\"$googleMapsApiKey\"")
        
        // Manifest placeholders for Google Maps API Key
        manifestPlaceholders["GOOGLE_MAPS_API_KEY"] = googleMapsApiKey
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            applicationIdSuffix = ""
            buildConfigField("String", "API_BASE_URL", "\"https://api.example.com\"")
            buildConfigField("String", "ENVIRONMENT_NAME", "\"Production\"")
            resValue("string", "app_name", "ComposeDemo")
            resValue("string", "environment", "Production")
        }
        create("stg") {
            isDebuggable = true
            applicationIdSuffix = ".stg"
            buildConfigField("String", "API_BASE_URL", "\"https://staging-api.example.com\"")
            buildConfigField("String", "ENVIRONMENT_NAME", "\"Staging\"")
            signingConfig = signingConfigs.getByName("debug")
            resValue("string", "app_name", "ComposeDemo Staging")
            resValue("string", "environment", "Staging")
        }
        create("dev") {
            isDebuggable = true
            applicationIdSuffix = ".dev"
            buildConfigField("String", "API_BASE_URL", "\"https://dev-api.example.com\"")
            buildConfigField("String", "ENVIRONMENT_NAME", "\"Development\"")
            signingConfig = signingConfigs.getByName("debug")
            resValue("string", "app_name", "ComposeDemo Dev")
            resValue("string", "environment", "Development")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    debugImplementation(compose.uiTooling)
}

// テストタスクの設定
tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
}

