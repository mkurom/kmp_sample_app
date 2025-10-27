package com.my.composedemo.util

/**
 * Common type conversion utilities between Kotlin and platform-specific types
 * This file provides expect declarations for platform-specific implementations
 */

/**
 * Represents a platform-independent rectangle
 */
data class Rect(
    val x: Float = 0f,
    val y: Float = 0f,
    val width: Float = 0f,
    val height: Float = 0f
) {
    fun toZeroRect(): Rect = Rect(0f, 0f, 0f, 0f)
}

/**
 * Represents a platform-independent point
 */
data class Point(
    val x: Float = 0f,
    val y: Float = 0f
) {
    fun toZeroPoint(): Point = Point(0f, 0f)
}

/**
 * Represents a platform-independent size
 */
data class Size(
    val width: Float = 0f,
    val height: Float = 0f
) {
    fun toZeroSize(): Size = Size(0f, 0f)
}

/**
 * Expect declaration for iOS CGRect conversion
 */
expect object NativeTypeConverter {
    /**
     * Converts platform-specific rect to common Rect
     */
    fun rectToCommon(rect: Any): Rect
    
    /**
     * Converts platform-specific point to common Point
     */
    fun pointToCommon(point: Any): Point
    
    /**
     * Converts platform-specific size to common Size
     */
    fun sizeToCommon(size: Any): Size
    
    /**
     * Converts common Rect to platform-specific rect
     */
    fun rectFromCommon(rect: Rect): Any
    
    /**
     * Converts common Point to platform-specific point
     */
    fun pointFromCommon(point: Point): Any
    
    /**
     * Converts common Size to platform-specific size
     */
    fun sizeFromCommon(size: Size): Any
}

/**
 * Extension functions for easier conversions
 */
object TypeConverter {
    inline fun <T> Any.toCommonType(converter: (Any) -> T): T = converter(this)
    
    inline fun <T> T.fromCommonType(converter: (T) -> Any): Any = converter(this)
}

