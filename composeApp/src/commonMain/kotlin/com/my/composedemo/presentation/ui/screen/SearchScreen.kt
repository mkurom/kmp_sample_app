package com.my.composedemo.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
// import com.my.composedemo.presentation.ui.components.MapView

@Composable
fun SearchScreen() {
    var searchText by remember { mutableStateOf("") }
    var selectedLocation by remember { mutableStateOf("Tokyo, Japan") }
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Search & Map",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Text(
                text = "Find locations and explore the map",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
        
        // Search field
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Search locations...") },
                modifier = Modifier.weight(1f)
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Button(
                onClick = {
                    if (searchText.isNotBlank()) {
                        selectedLocation = searchText
                    }
                }
            ) {
                Text("Search")
            }
        }
        
        // Current location display
        if (selectedLocation.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Blue.copy(alpha = 0.1f))
            ) {
                Text(
                    text = "📍 $selectedLocation",
                    modifier = Modifier.padding(12.dp),
                    fontSize = 14.sp,
                    color = Color.Blue
                )
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Placeholder for Map (Google Maps temporarily disabled)
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.3f))
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🗺️\nMap View\n(Coming Soon)",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
        
        /* Temporarily disabled - Google Maps integration
        MapView(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            onMapClick = {
                // Handle map click - could open full screen map or show details
                // This will be implemented with actual Google Maps integration
            }
        )
        */
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}
