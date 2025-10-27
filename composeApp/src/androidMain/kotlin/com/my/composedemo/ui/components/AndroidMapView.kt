package com.my.composedemo.presentation.ui.components

// import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
// import com.google.android.gms.maps.model.CameraPosition
// import com.google.android.gms.maps.model.LatLng
// import com.google.maps.android.compose.GoogleMap
// import com.google.maps.android.compose.rememberCameraPositionState
// import com.my.composedemo.BuildConfig

// Temporarily disabled - Google Maps integration
/*
@Composable
actual fun MapView(
    modifier: Modifier,
    onMapClick: (() -> Unit)?
) {
    // APIキーの確認
    Log.d("GoogleMaps", "API Key: ${BuildConfig.GOOGLE_MAPS_API_KEY}")
    
    val tokyo = LatLng(35.6762, 139.6503)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(tokyo, 10f)
    }

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapClick = { onMapClick?.invoke() }
    )
}
*/
