package com.my.composedemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import composedemo.composeapp.generated.resources.Res
import composedemo.composeapp.generated.resources.compose_multiplatform
import composedemo.composeapp.generated.resources.eg
import composedemo.composeapp.generated.resources.fr
import composedemo.composeapp.generated.resources.id
import composedemo.composeapp.generated.resources.jp
import composedemo.composeapp.generated.resources.mx
import kotlinx.datetime.Clock
import kotlinx.datetime.IllegalTimeZoneException
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.DrawableResource

@Composable
@Preview
fun App(
    countries: List<Country> = defaultCountries,
    onNavigateToSwiftUI: (() -> Unit)? = null,
    onNavigateToNative: (() -> Unit)? = null
) {
    var appState by remember { mutableStateOf(AppState.SPLASH) }
    val navigationState = remember { NavigationState() }
    
    MaterialTheme {
        when (appState) {
            AppState.SPLASH -> {
                SplashScreen(
                    onNavigateToHome = {
                        appState = AppState.HOME
                    }
                )
            }
            AppState.HOME -> {
                PlatformNavigation(
                    navigationState = navigationState,
                    onNavigateToNative = onNavigateToNative,
                    onNavigateToSwiftUI = onNavigateToSwiftUI
                )
            }
        }
    }
}

// プラットフォーム固有のナビゲーション
@Composable
expect fun PlatformNavigation(
    navigationState: NavigationState,
    onNavigateToNative: (() -> Unit)?,
    onNavigateToSwiftUI: (() -> Unit)?
)

data class Country(
    val name: String,
    val zone: TimeZone,
    val image: DrawableResource
)

fun currentTimeAt(location: String, zone: TimeZone): String {
    fun LocalTime.formatted() = "$hour:$minute:$second"

    val time = Clock.System.now()
    val localTime = time.toLocalDateTime(zone).time

    return "The time in $location is ${localTime.formatted()}"
}

val defaultCountries = listOf(
    Country("Japan", TimeZone.of("Asia/Tokyo"), Res.drawable.jp),
    Country("France", TimeZone.of("Europe/Paris"), Res.drawable.fr),
    Country("Mexico", TimeZone.of("America/Mexico_City"), Res.drawable.mx),
    Country("Indonesia", TimeZone.of("Asia/Jakarta"), Res.drawable.id),
    Country("Egypt", TimeZone.of("Africa/Cairo"), Res.drawable.eg),
)
