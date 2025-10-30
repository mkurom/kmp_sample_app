package com.my.composedemo.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.painterResource
import composedemo.composeapp.generated.resources.Res
import composedemo.composeapp.generated.resources.home
import composedemo.composeapp.generated.resources.search
import composedemo.composeapp.generated.resources.person
import composedemo.composeapp.generated.resources.settings
import composedemo.composeapp.generated.resources.arrow_back

/**
 * iOS版ではSVGファイルをPainterとして読み込む
 */

actual fun AppIconHome(): ImageVector? = null
actual fun AppIconSearch(): ImageVector? = null
/**
 * Provides the platform-specific vector drawable for the profile icon on iOS.
 *
 * @return The `ImageVector` for the profile icon, or `null` if a vector asset is not available on this platform.
 */
actual fun AppIconProfile(): ImageVector? = null
/**
 * Platform-specific placeholder for the Settings icon as a vector.
 *
 * @return An ImageVector for the Settings icon, or `null` on iOS where vector icons are not provided.
 */
actual fun AppIconSettings(): ImageVector? = null
/**
 * Provides the platform-specific ImageVector for the Person app icon.
 *
 * @return `ImageVector` for the Person icon, or `null` if not available on this platform.
 */
actual fun AppIconPerson(): ImageVector? = null
/**
 * Platform-specific accessor for the Arrow Back vector icon on iOS.
 *
 * @return An ImageVector representing the arrow-back icon, or `null` when a vector asset is not available on this platform.
 */
actual fun AppIconArrowBack(): ImageVector? = null

/**
 * Provides a Painter for the app's Home icon on iOS.
 *
 * @return A Painter that loads the Home drawable resource (`Res.drawable.home`), or `null` if the resource is unavailable.
 */
@Composable
actual fun AppIconHomePainter(): Painter? = painterResource(Res.drawable.home)

@Composable
actual fun AppIconSearchPainter(): Painter? = painterResource(Res.drawable.search)

@Composable
actual fun AppIconProfilePainter(): Painter? = painterResource(Res.drawable.person)

@Composable
actual fun AppIconSettingsPainter(): Painter? = painterResource(Res.drawable.settings)

/**
 * Provides a platform-specific Painter for the person/profile icon.
 *
 * @return The `Painter` for the person drawable resource, or `null` if it cannot be loaded.
 */
@Composable
actual fun AppIconPersonPainter(): Painter? = painterResource(Res.drawable.person)

/**
 * Provides the platform-specific painter for the "arrow back" icon.
 *
 * @return A `Painter` for the "arrow back" drawable resource, or `null` if it cannot be loaded.
 */
@Composable
actual fun AppIconArrowBackPainter(): Painter? = painterResource(Res.drawable.arrow_back)