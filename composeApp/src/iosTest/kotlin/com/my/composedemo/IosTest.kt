package com.my.composedemo

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class IosTest {

    @Test
    fun testIosPlatform() {
        val platform = Platform()
        val platformName = platform.name
        assertEquals("iOS", platformName)
    }
    
    @Test
    fun testGreetingOnIos() {
        val greeting = Greeting().greet()
        assertTrue(greeting.contains("Hello"), "Greeting should contain 'Hello'")
        assertTrue(greeting.contains("iOS"), "Greeting should contain 'iOS'")
    }
    
    @Test
    fun testMathOperationsOnIos() {
        assertEquals(20, 15 + 5)
        assertEquals(75, 15 * 5)
        assertEquals(3, 15 / 5)
    }
    
    @Test
    fun testStringOperations() {
        val testString = "Hello iOS"
        assertTrue(testString.startsWith("Hello"))
        assertTrue(testString.endsWith("iOS"))
        assertEquals(9, testString.length)
    }
}
