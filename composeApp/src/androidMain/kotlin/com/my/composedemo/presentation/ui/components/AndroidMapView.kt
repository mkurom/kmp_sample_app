package com.my.composedemo.platform.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
actual fun MapView(
    modifier: Modifier,
    latitude: Double,
    longitude: Double,
    zoom: Float,
    onMapClick: (() -> Unit)?
) {
    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(latitude, longitude, zoom) {
        cameraPositionState.position = CameraPosition.fromLatLngZoom(LatLng(latitude, longitude), zoom)
    }

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapClick = { onMapClick?.invoke() }
    )
}

