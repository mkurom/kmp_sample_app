package com.my.composedemo.util

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
    actual fun rectToCommon(rect: Any): Rect {
        val cgRect = rect as? platform.CoreGraphics.CGRect
        if (cgRect != null) {
            @Suppress("CAST_NEVER_SUCCEEDS")
            return Rect(
                x = cgRect.origin.x.toFloat(),
                y = cgRect.origin.y.toFloat(),
                width = cgRect.size.width.toFloat(),
                height = cgRect.size.height.toFloat()
            )
        }
        return Rect(0f, 0f, 0f, 0f)
    }
    
    /**
     * Converts CGPoint to common Point
     */
    actual fun pointToCommon(point: Any): Point {
        val cgPoint = point as? platform.CoreGraphics.CGPoint
        if (cgPoint != null) {
            return Point(
                x = cgPoint.x.toFloat(),
                y = cgPoint.y.toFloat()
            )
        }
        return Point(0f, 0f)
    }
    
    /**
     * Converts CGSize to common Size
     */
    actual fun sizeToCommon(size: Any): Size {
        val cgSize = size as? platform.CoreGraphics.CGSize
        if (cgSize != null) {
            return Size(
                width = cgSize.width.toFloat(),
                height = cgSize.height.toFloat()
            )
        }
        return Size(0f, 0f)
    }
    
    /**
     * Creates CGRect from common Rect
     */
    actual fun rectFromCommon(rect: Rect): Any {
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
    actual fun pointFromCommon(point: Point): Any {
        val cgPoint = cValue<CGPoint> {
            x = point.x.toDouble()
            y = point.y.toDouble()
        }
        return cgPoint
    }
    
    /**
     * Creates CGSize from common Size
     */
    actual fun sizeFromCommon(size: Size): Any {
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
    fun CGRect.toRect(): Rect {
        @Suppress("CAST_NEVER_SUCCEEDS")
        return Rect(
            x = this.origin.x.toFloat(),
            y = this.origin.y.toFloat(),
            width = this.size.width.toFloat(),
            height = this.size.height.toFloat()
        )
    }
    
    /**
     * Converts Rect to CValue<CGRect>
     */
    fun Rect.toCGRectValue(): kotlinx.cinterop.CValue<platform.CoreGraphics.CGRect> {
        return cValue<platform.CoreGraphics.CGRect> {
            origin.x = x.toDouble()
            origin.y = y.toDouble()
            size.width = width.toDouble()
            size.height = height.toDouble()
        }
    }
}

