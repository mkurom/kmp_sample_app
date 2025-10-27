package com.my.composedemo.platform.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun MapView(
    modifier: Modifier = Modifier,
    onMapClick: (() -> Unit)? = null
)

