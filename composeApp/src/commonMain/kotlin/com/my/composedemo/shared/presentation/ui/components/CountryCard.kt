package com.my.composedemo.shared.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import com.my.composedemo.shared.domain.model.Country

/**
 * Displays a clickable card showing a country's image, name, and a time string.
 *
 * @param country The country model whose `image` is shown and whose `name` is used as the title and image content description.
 * @param currentTime Text displayed below the country name.
 * @param onClick Callback invoked when the card is clicked.
 */
@Composable
fun CountryCard(
    country: Country,
    currentTime: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
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
            
            Column {
                Text(
                    text = country.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = currentTime,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
