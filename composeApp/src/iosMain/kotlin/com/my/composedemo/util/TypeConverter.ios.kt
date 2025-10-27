package com.my.composedemo.platform.util

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cValue
import platform.CoreGraphics.*

/**
 * iOS implementation of NativeTypeConverter
 * Handles conversion between iOS CoreGraphics types and common Kotlin types
 */
@OptIn(ExperimentalForeignApi::class)
actual object NativeTypeConverter {
    
    /**
     * Converts CGRect to common Rect
     */
    actual fun rectToCommon(rect: Any): com.my.composedemo.platform.util.Rect {
        val cgRect = rect as? platform.CoreGraphics.CGRect
        if (cgRect != null) {
            @Suppress("CAST_NEVER_SUCCEEDS")
            return com.my.composedemo.platform.util.Rect(
                x = cgRect.origin.x.toFloat(),
                y = cgRect.origin.y.toFloat(),
                width = cgRect.size.width.toFloat(),
                height = cgRect.size.height.toFloat()
            )
        }
        return com.my.composedemo.platform.util.Rect(0f, 0f, 0f, 0f)
    }
    
    /**
     * Converts CGPoint to common Point
     */
    actual fun pointToCommon(point: Any): com.my.composedemo.platform.util.Point {
        val cgPoint = point as? platform.CoreGraphics.CGPoint
        if (cgPoint != null) {
            return com.my.composedemo.platform.util.Point(
                x = cgPoint.x.toFloat(),
                y = cgPoint.y.toFloat()
            )
        }
        return com.my.composedemo.platform.util.Point(0f, 0f)
    }
    
    /**
     * Converts CGSize to common Size
     */
    actual fun sizeToCommon(size: Any): com.my.composedemo.platform.util.Size {
        val cgSize = size as? platform.CoreGraphics.CGSize
        if (cgSize != null) {
            return com.my.composedemo.platform.util.Size(
                width = cgSize.width.toFloat(),
                height = cgSize.height.toFloat()
            )
        }
        return com.my.composedemo.platform.util.Size(0f, 0f)
    }
    
    /**
     * Creates CGRect from common Rect
     */
    actual fun rectFromCommon(rect: com.my.composedemo.platform.util.Rect): Any {
        val cgRect = cValue<CGRect> {
            origin.x = rect.x.toDouble()
            origin.y = rect.y.toDouble()
            size.width = rect.width.toDouble()
            size.height = rect.height.toDouble()
        }
        return cgRect
    }
    
    /**
     * Creates CGPoint from common Point
     */
    actual fun pointFromCommon(point: com.my.composedemo.platform.util.Point): Any {
        val cgPoint = cValue<CGPoint> {
            x = point.x.toDouble()
            y = point.y.toDouble()
        }
        return cgPoint
    }
    
    /**
     * Creates CGSize from common Size
     */
    actual fun sizeFromCommon(size: com.my.composedemo.platform.util.Size): Any {
        val cgSize = cValue<CGSize> {
            width = size.width.toDouble()
            height = size.height.toDouble()
        }
        return cgSize
    }
}

/**
 * Helper extensions for iOS specific conversions
 */
@OptIn(ExperimentalForeignApi::class)
object iOSTypeHelpers {
    /**
     * Creates a zero CGRect
     */
    fun createZeroCGRect() = cValue<CGRect> {
        origin.x = 0.0
        origin.y = 0.0
        size.width = 0.0
        size.height = 0.0
    }
    
    /**
     * Converts CGRect to Rect
     */
    fun CGRect.toRect(): com.my.composedemo.platform.util.Rect {
        @Suppress("CAST_NEVER_SUCCEEDS")
        return com.my.composedemo.platform.util.Rect(
            x = this.origin.x.toFloat(),
            y = this.origin.y.toFloat(),
            width = this.size.width.toFloat(),
            height = this.size.height.toFloat()
        )
    }
    
    /**
     * Converts Rect to CValue<CGRect>
     */
    fun com.my.composedemo.platform.util.Rect.toCGRectValue(): kotlinx.cinterop.CValue<platform.CoreGraphics.CGRect> {
        return cValue<platform.CoreGraphics.CGRect> {
            origin.x = x.toDouble()
            origin.y = y.toDouble()
            size.width = width.toDouble()
            size.height = height.toDouble()
        }
    }
}

