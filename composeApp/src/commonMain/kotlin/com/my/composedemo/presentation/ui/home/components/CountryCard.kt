package com.my.composedemo.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.my.composedemo.domain.model.Country
import org.jetbrains.compose.resources.painterResource

/**
 * 国情報カードコンポーネント
 * ホーム画面固有のコンポーネント
 */
@Composable
fun CountryCard(
    country: Country,
    onToggleFavorite: () -> Unit,
    onGetCurrentTime: () -> String
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(country.image),
                contentDescription = country.name,
                modifier = Modifier.size(48.dp)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = country.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = onGetCurrentTime(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            
                    IconButton(onClick = onToggleFavorite) {
                        Text(
                            text = if (country.isFavorite) "❤️" else "🤍",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
        }
    }
}
