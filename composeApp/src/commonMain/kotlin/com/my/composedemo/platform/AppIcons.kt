package com.my.composedemo.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * プラットフォーム固有のアイコンを定義
 */
expect fun AppIconHome(): ImageVector?
expect fun AppIconSearch(): ImageVector?
expect fun AppIconProfile(): ImageVector?
expect fun AppIconSettings(): ImageVector?
expect fun AppIconPerson(): ImageVector?
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
@Composable
expect fun AppIconSettingsPainter(): Painter?
@Composable
expect fun AppIconPersonPainter(): Painter?
@Composable
expect fun AppIconArrowBackPainter(): Painter?

