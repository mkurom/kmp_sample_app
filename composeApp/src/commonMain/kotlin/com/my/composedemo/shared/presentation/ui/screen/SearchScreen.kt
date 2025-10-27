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
                onSearch = { selectedLocation = searchText }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Google Maps View
            MapView(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
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

