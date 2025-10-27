package com.my.composedemo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Android版ではMaterial Iconsを使用
 */

actual fun AppIconHome(): ImageVector? = Icons.Filled.Home
actual fun AppIconSearch(): ImageVector? = Icons.Outlined.Search
actual fun AppIconProfile(): ImageVector? = Icons.Filled.Person
actual fun AppIconSettings(): ImageVector? = Icons.Filled.Settings
actual fun AppIconPerson(): ImageVector? = Icons.Filled.Person

// Android版ではPainterはnullを返す（Material IconsのImageVectorを使用）
@Composable
actual fun AppIconHomePainter(): Painter? = null
@Composable
actual fun AppIconSearchPainter(): Painter? = null
@Composable
actual fun AppIconProfilePainter(): Painter? = null
@Composable
actual fun AppIconSettingsPainter(): Painter? = null
@Composable
actual fun AppIconPersonPainter(): Painter? = null
