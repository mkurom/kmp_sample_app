package com.my.composedemo

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import composedemo.composeapp.generated.resources.Res
import composedemo.composeapp.generated.resources.compose_multiplatform

@Composable
fun SplashScreen(
    onNavigateToHome: () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }
    
    // フェードインアニメーション
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(1000),
        label = "alpha"
    )
    
    // スケールアニメーション
    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.8f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )
    
    // スプラッシュ画面の表示
    LaunchedEffect(Unit) {
        isVisible = true
        delay(2000) // 2秒間表示
        isLoading = false
        delay(500) // フェードアウト時間
        onNavigateToHome()
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .alpha(alpha),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // ロゴ画像
            Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(120.dp)
                    .alpha(alpha)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // アプリ名
            Text(
                text = "ComposeDemo",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                ),
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(alpha)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // サブタイトル
            Text(
                text = "Kotlin Multiplatform App",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(alpha)
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            // ローディングインジケーター
            if (isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 3.dp,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

// アプリの状態を管理するenum
enum class AppState {
    SPLASH,
    HOME
}
