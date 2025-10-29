package com.my.composedemo.platform.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import platform.Foundation.NSNumber
import platform.UIKit.UIViewController
import platform.Foundation.setValue
import platform.Foundation.NSLog

/**
 * iOS での MapView 実装
 *
 * SPM で導入済みの Google Maps SDK を使用
 */
@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
@Composable
actual fun MapView(
    modifier: Modifier,
    latitude: Double,
    longitude: Double,
    zoom: Float,
    onMapClick: (() -> Unit)?
) {
    val mapViewController = remember {
        object : KoinComponent {
            fun getViewController(): UIViewController = get()
        }.getViewController()
    }

    UIKitView(
        factory = {
            mapViewController.view.also {}
        },
        modifier = modifier,
        update = { _ ->
            mapViewController.setValue(NSNumber(latitude), forKey = "latitude")
            mapViewController.setValue(NSNumber(longitude), forKey = "longitude")
            mapViewController.setValue(NSNumber(zoom.toDouble()), forKey = "zoom")
        }
    )
}
