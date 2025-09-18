package com.my.composedemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(
                onNavigateToNative = {
                    // Android Native Activityに遷移
                    val intent = Intent(this, AndroidNativeActivity::class.java)
                    startActivity(intent)
                }
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}