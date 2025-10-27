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

/**
 * iOS版ではSVGファイルをPainterとして読み込む
 */

actual fun AppIconHome(): ImageVector? = null
actual fun AppIconSearch(): ImageVector? = null
actual fun AppIconProfile(): ImageVector? = null
actual fun AppIconSettings(): ImageVector? = null
actual fun AppIconPerson(): ImageVector? = null

@Composable
actual fun AppIconHomePainter(): Painter? = painterResource(Res.drawable.home)

@Composable
actual fun AppIconSearchPainter(): Painter? = painterResource(Res.drawable.search)

@Composable
actual fun AppIconProfilePainter(): Painter? = painterResource(Res.drawable.person)

@Composable
actual fun AppIconSettingsPainter(): Painter? = painterResource(Res.drawable.settings)

@Composable
actual fun AppIconPersonPainter(): Painter? = painterResource(Res.drawable.person)
