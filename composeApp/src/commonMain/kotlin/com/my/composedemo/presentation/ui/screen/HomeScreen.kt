package com.my.composedemo.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.my.composedemo.presentation.ui.components.CountryCard
import com.my.composedemo.presentation.ui.model.Country
import composedemo.composeapp.generated.resources.Res
import composedemo.composeapp.generated.resources.eg
import composedemo.composeapp.generated.resources.fr
import composedemo.composeapp.generated.resources.id
import composedemo.composeapp.generated.resources.jp
import composedemo.composeapp.generated.resources.mx
import kotlinx.datetime.TimeZone

val defaultCountries = listOf(
    Country("Japan", TimeZone.of("Asia/Tokyo"), Res.drawable.jp),
    Country("France", TimeZone.of("Europe/Paris"), Res.drawable.fr),
    Country("Mexico", TimeZone.of("America/Mexico_City"), Res.drawable.mx),
    Country("Indonesia", TimeZone.of("Asia/Jakarta"), Res.drawable.id),
    Country("Egypt", TimeZone.of("Africa/Cairo"), Res.drawable.eg),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Home")
                }
            )
        },
    ) { innerPadding ->
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
            
            items(defaultCountries) { country ->
                CountryCard(country = country)
            }

            items(defaultCountries) { country ->
                CountryCard(country = country)
            }

            items(defaultCountries) { country ->
                CountryCard(country = country)
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
