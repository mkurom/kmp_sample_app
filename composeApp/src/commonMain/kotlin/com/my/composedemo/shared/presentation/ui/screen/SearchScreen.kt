package com.my.composedemo.shared.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.my.composedemo.platform.components.MapView

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var searchText by remember { mutableStateOf("") }
    var selectedLocation by remember { mutableStateOf("Tokyo, Japan") }

    // 地図のカメラ位置を管理するstate
    var mapLatitude by remember { mutableStateOf(35.6762) }
    var mapLongitude by remember { mutableStateOf(139.6503) }
    var mapZoom by remember { mutableStateOf(10f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Search")
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
        ) {
            SearchField(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                onSearch = {
                    selectedLocation = searchText
                    // ダミーのジオコーディング処理
                    when (selectedLocation.lowercase()) {
                        "tokyo" -> {
                            mapLatitude = 35.6895
                            mapLongitude = 139.6917
                            mapZoom = 12f
                        }
                        "london" -> {
                            mapLatitude = 51.5074
                            mapLongitude = 0.1278
                            mapZoom = 12f
                        }
                        "new york" -> {
                            mapLatitude = 40.7128
                            mapLongitude = -74.0060
                            mapZoom = 12f
                        }
                        else -> {
                            mapLatitude = 35.6762
                            mapLongitude = 139.6503
                            mapZoom = 10f
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Google Maps View
            MapView(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                latitude = mapLatitude,
                longitude = mapLongitude,
                zoom = mapZoom,
                onMapClick = {
                    // Handle map click - could open full screen map or show details
                    // This will be implemented with actual Google Maps integration
                }
            )
        }
    }
}

@Composable
fun SearchField(
    searchText: String = "",
    onSearchTextChange: (String) -> Unit = {},
    onSearch: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            label = { Text("Search locations...") },
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = {
                if (searchText.isNotBlank()) {
                    onSearch()
                }
            }
        ) {
            Text("Search")
        }
    }
}

