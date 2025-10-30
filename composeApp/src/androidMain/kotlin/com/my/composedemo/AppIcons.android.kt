package com.my.composedemo.platform

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
/**
 * Provides the app's profile (person) icon.
 *
 * @return The Material `Person` icon as an ImageVector, or `null` if unavailable.
 */
actual fun AppIconProfile(): ImageVector? = Icons.Filled.Person
/**
 * Provides the Material "Settings" icon as an ImageVector.
 *
 * @return The Material `Settings` ImageVector, or `null` if the icon is unavailable.
 */
actual fun AppIconSettings(): ImageVector? = Icons.Filled.Settings
/**
 * Provides the platform-specific ImageVector for the app's person/profile icon.
 *
 * @return The Material `Person` icon as an ImageVector, or `null` if unavailable on the current platform.
 */
actual fun AppIconPerson(): ImageVector? = Icons.Filled.Person
/**
 * Provides the platform-specific "arrow back" icon as an ImageVector.
 *
 * @return The ImageVector for the platform's "arrow back" icon, or `null` if unavailable.
 */
actual fun AppIconArrowBack(): ImageVector? = Icons.Filled.ArrowBack

/**
 * Provide a platform-specific Painter for the Home icon on Android.
 *
 * On Android this returns `null` because the Material icon is supplied as an `ImageVector`
 * rather than a `Painter`.
 *
 * @return `null` on Android to indicate the Painter is not provided.
 */
@Composable
actual fun AppIconHomePainter(): Painter? = null
@Composable
actual fun AppIconSearchPainter(): Painter? = null
@Composable
actual fun AppIconProfilePainter(): Painter? = null
/**
 * Provides a Painter for the Settings icon.
 *
 * @return A `Painter` for the Settings icon, or `null` on Android where ImageVector-based icons are used.
 */
@Composable
actual fun AppIconSettingsPainter(): Painter? = null
/**
 * Obtain the platform-specific Painter for the Person icon on Android.
 *
 * @return `null` because a Painter implementation is not provided on Android.
 */
@Composable
actual fun AppIconPersonPainter(): Painter? = null
/**
 * Provides a Painter for the app's ArrowBack icon for platforms that supply one.
 *
 * @return A `Painter` for the ArrowBack icon, or `null` on Android (an `ImageVector` is used instead).
 */
@Composable
actual fun AppIconArrowBackPainter(): Painter? = null