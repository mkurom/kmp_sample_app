package com.my.composedemo

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ComposeAppCommonTest {

    @Test
    fun example() {
        assertEquals(3, 1 + 2)
    }
    
    @Test
    fun testPlatformName() {
        val platform = getPlatform()
        val platformName = platform.name
        assertTrue(platformName.isNotEmpty(), "Platform name should not be empty")
    }
    
    @Test
    fun testGreeting() {
        val greeting = Greeting().greet()
        assertTrue(greeting.contains("Hello"), "Greeting should contain 'Hello'")
    }
    
    @Test
    fun testMathOperations() {
        assertEquals(10, 5 + 5)
        assertEquals(25, 5 * 5)
        assertEquals(2, 10 / 5)
    }
}