package com.my.composedemo.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * プラットフォーム固有のアイコンを定義
 */
expect fun AppIconHome(): ImageVector?
expect fun AppIconSearch(): ImageVector?
/**
 * Platform-specific vector icon representing a user profile.
 *
 * @return An ImageVector for the profile icon, or `null` if the platform does not supply one.
 */
expect fun AppIconProfile(): ImageVector?
/**
 * Provides the platform-specific vector icon for the app's settings control.
 *
 * @return An `ImageVector` representing the settings icon, or `null` if the vector is unavailable on the current platform.
 */
expect fun AppIconSettings(): ImageVector?
/**
 * Represents the platform-specific "person" icon.
 *
 * @return The platform's `ImageVector` for the person icon, or `null` if the icon is not provided on the current platform.
 */
expect fun AppIconPerson(): ImageVector?
/**
 * Provides the platform-specific "arrow back" icon as an ImageVector.
 *
 * @return The platform's `ImageVector` for the back arrow, or `null` if unavailable.
 */
expect fun AppIconArrowBack(): ImageVector?

/**
 * iOS用のPainter
 */
@Composable
expect fun AppIconHomePainter(): Painter?
@Composable
expect fun AppIconSearchPainter(): Painter?
@Composable
expect fun AppIconProfilePainter(): Painter?
/**
 * Provides the platform-specific settings icon for use in Compose UI.
 *
 * @return A `Painter` representing the settings icon on the current platform, or `null` if the icon is not available.
 */
@Composable
expect fun AppIconSettingsPainter(): Painter?
/**
 * Provides a platform-specific Painter for the person (user/profile) icon.
 *
 * @return A Painter for the person icon, or `null` if the icon is unavailable on the current platform.
 */
@Composable
expect fun AppIconPersonPainter(): Painter?
/**
 * Provides a platform-specific Painter for the app's back arrow icon.
 *
 * @return A Painter for the back arrow icon, or `null` if the icon is unavailable on the current platform.
 */
@Composable
expect fun AppIconArrowBackPainter(): Painter?
