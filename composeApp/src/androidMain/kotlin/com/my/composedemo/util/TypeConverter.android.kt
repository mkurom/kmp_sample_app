package com.my.composedemo.util

import android.graphics.RectF
import android.graphics.PointF
import android.graphics.Rect
import android.graphics.Point

/**
 * Android implementation of NativeTypeConverter
 * Handles conversion between Android graphics types and common Kotlin types
 */
actual object NativeTypeConverter {
    
    /**
     * Converts Android Rect to common Rect
     */
    actual fun rectToCommon(rect: Any): com.my.composedemo.util.Rect {
        return when (rect) {
            is Rect -> com.my.composedemo.util.Rect(
                x = rect.left.toFloat(),
                y = rect.top.toFloat(),
                width = rect.width().toFloat(),
                height = rect.height().toFloat()
            )
            is RectF -> com.my.composedemo.util.Rect(
                x = rect.left,
                y = rect.top,
                width = rect.width(),
                height = rect.height()
            )
            else -> com.my.composedemo.util.Rect(0f, 0f, 0f, 0f)
        }
    }
    
    /**
     * Converts Android Point to common Point
     */
    actual fun pointToCommon(point: Any): com.my.composedemo.util.Point {
        val androidPoint = point as? Point
        if (androidPoint != null) {
            return com.my.composedemo.util.Point(
                x = androidPoint.x.toFloat(),
                y = androidPoint.y.toFloat()
            )
        }
        return com.my.composedemo.util.Point(0f, 0f)
    }
    
    /**
     * Converts size to common Size
     */
    actual fun sizeToCommon(size: Any): com.my.composedemo.util.Size {
        val sizeInt = size as? android.util.Size
        if (sizeInt != null) {
            return com.my.composedemo.util.Size(
                width = sizeInt.width.toFloat(),
                height = sizeInt.height.toFloat()
            )
        }
        
        val sizeF = size as? android.util.SizeF
        if (sizeF != null) {
            return com.my.composedemo.util.Size(
                width = sizeF.width,
                height = sizeF.height
            )
        }
        
        return com.my.composedemo.util.Size(0f, 0f)
    }
    
    /**
     * Creates Android Rect from common Rect
     */
    actual fun rectFromCommon(rect: com.my.composedemo.util.Rect): Any {
        return Rect(
            rect.x.toInt(),
            rect.y.toInt(),
            (rect.x + rect.width).toInt(),
            (rect.y + rect.height).toInt()
        )
    }
    
    /**
     * Creates Android PointF from common Point
     */
    actual fun pointFromCommon(point: com.my.composedemo.util.Point): Any {
        return PointF(point.x, point.y)
    }
    
    /**
     * Creates Android Size from common Size
     */
    actual fun sizeFromCommon(size: com.my.composedemo.util.Size): Any {
        return android.util.SizeF(size.width, size.height)
    }
}

/**
 * Helper extensions for Android specific conversions
 */
object AndroidTypeHelpers {
    /**
     * Converts Rect to common Rect
     */
    fun Rect.toCommonRect(): com.my.composedemo.util.Rect {
        return com.my.composedemo.util.Rect(
            x = this.left.toFloat(),
            y = this.top.toFloat(),
            width = this.width().toFloat(),
            height = this.height().toFloat()
        )
    }
    
    /**
     * Converts RectF to common Rect
     */
    fun RectF.toCommonRect(): com.my.composedemo.util.Rect {
        return com.my.composedemo.util.Rect(
            x = this.left,
            y = this.top,
            width = this.width(),
            height = this.height()
        )
    }
    
    /**
     * Converts common Rect to android.graphics.Rect
     */
    fun com.my.composedemo.util.Rect.toAndroidRect(): Rect {
        return Rect(
            x.toInt(),
            y.toInt(),
            (x + width).toInt(),
            (y + height).toInt()
        )
    }
    
    /**
     * Converts common Rect to RectF
     */
    fun com.my.composedemo.util.Rect.toAndroidRectF(): RectF {
        return RectF(
            x,
            y,
            x + width,
            y + height
        )
    }
}
