package com.my.composedemo.shared.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.my.composedemo.shared.presentation.ui.components.CountryCard
import com.my.composedemo.shared.presentation.viewmodel.HomeViewModel
import org.koin.compose.koinInject

/**
 * HomeScreen - 国の一覧を表示
 * @param onNavigateToDetail 国詳細画面への遷移コールバック（countryIdを渡す）
 */
/**
 * Displays the Home screen with loading, error, and country-list states.
 *
 * Shows a top app bar titled "Home". While loading, displays a centered progress indicator;
 * when an error is present, displays the error message and a Retry button that clears the error;
 * otherwise displays a scrollable list of countries and calls the navigation callback when an item is selected.
 *
 * @param homeViewModel ViewModel that provides the screen's UI state and helpers; injected by default.
 * @param onNavigateToDetail Callback invoked with the selected country's id.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinInject(),
    onNavigateToDetail: (countryId: String) -> Unit = {}
) {
    val uiState by homeViewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Home")
                }
            )
        },
    ) { innerPadding ->
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            uiState.error != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Error: ${uiState.error}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { homeViewModel.clearError() }) {
                        Text("Retry")
                    }
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Text(
                            text = "Explore countries around the world",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    
                    items(uiState.countries) { country ->
                        CountryCard(
                            country = country,
                            currentTime = homeViewModel.getCurrentTime(country),
                            onClick = {
                                onNavigateToDetail(country.id)
                            }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Displays a design-time preview of HomeScreen.
 *
 * Uses a no-op navigation callback so the composable can be rendered in tooling without navigation side effects.
 */
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(onNavigateToDetail = {})
}