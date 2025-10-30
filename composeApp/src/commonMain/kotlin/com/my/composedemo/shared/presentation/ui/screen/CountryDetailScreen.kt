package com.my.composedemo.shared.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import com.my.composedemo.platform.AppIconArrowBack
import com.my.composedemo.platform.AppIconArrowBackPainter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import com.my.composedemo.shared.domain.model.Country
import com.my.composedemo.shared.domain.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

/**
 * CountryDetailScreen - 国の詳細情報を表示
 * @param countryId 表示する国のID
 * @param onBack 戻るボタンクリック時のコールバック
 */
/**
 * Display detailed information for a country identified by its ID.
 *
 * Loads the country data and shows one of three states: a centered progress indicator while
 * loading, an error message with a Back button when loading fails or the ID is missing, or the
 * country's details (flag, name, time zone, and current time) on success.
 *
 * @param countryId The identifier of the country to display; when `null` an error state is shown.
 * @param onBack Callback invoked when the user presses the top app bar navigation or the Back button.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDetailScreen(
    countryId: String?,
    onBack: () -> Unit = {},
    countryRepository: CountryRepository? = null
) {
    val repo: CountryRepository = countryRepository ?: koinInject()
    var country by remember { mutableStateOf<Country?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    
    LaunchedEffect(countryId) {
        if (countryId != null) {
            isLoading = true
            error = null
            try {
                country = withContext(Dispatchers.Default) {
                    repo.getCountryById(countryId)
                }
                if (country == null) {
                    error = "Country not found"
                }
            } catch (e: Exception) {
                error = e.message ?: "Failed to load country"
            } finally {
                isLoading = false
            }
        } else {
            error = "Country ID is required"
            isLoading = false
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Country Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        val painter = AppIconArrowBackPainter()
                        when {
                            painter != null -> {
                                // SVG使用（iOS）
                                Image(
                                    painter = painter,
                                    contentDescription = "Back",
                                    modifier = Modifier.size(24.dp),
                                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                                        MaterialTheme.colorScheme.onSurface
                                    )
                                )
                            }
                            AppIconArrowBack() != null -> {
                                // Material Icons使用（Android）
                                Icon(
                                    imageVector = AppIconArrowBack()!!,
                                    contentDescription = "Back"
                                )
                            }
                            else -> {
                                // フォールバック（テキストで表示）
                                Text(
                                    text = "←",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            error != null || country == null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = error ?: "Country not found",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onBack) {
                        Text("Back")
                    }
                }
            }
            else -> {
                val currentCountry = country
                if (currentCountry != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        // フラグ画像
                        Image(
                            painter = painterResource(currentCountry.image),
                            contentDescription = currentCountry.name,
                            modifier = Modifier.size(120.dp)
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // 国名
                        Text(
                            text = currentCountry.name,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineLarge
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // タイムゾーン情報
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text(
                                    text = "Time Zone",
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = currentCountry.timeZone.id,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                        
                        // 現在時刻
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text(
                                    text = "Current Time",
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = getCurrentTime(currentCountry),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Format the current local time for the given country.
 *
 * @param country The country whose time zone and name are used to compute the local time.
 * @return A message like "The time in {country.name} is H:M:S", or "Error getting time" if the time cannot be determined.
 */
private fun getCurrentTime(country: Country): String {
    return try {
        val time = Clock.System.now()
        val localTime = time.toLocalDateTime(country.timeZone).time
        "The time in ${country.name} is ${localTime.hour}:${localTime.minute}:${localTime.second}"
    } catch (e: Exception) {
        "Error getting time"
    }
}

/**
 * Renders a preview of CountryDetailScreen using a sample country ID for design-time previewing.
 *
 * This preview displays the CountryDetailScreen composable with `countryId = "us"` and a no-op back action.
 */
@Preview
@Composable
fun CountryDetailScreenPreview() {
    CountryDetailScreen(countryId = "us", onBack = {})
}