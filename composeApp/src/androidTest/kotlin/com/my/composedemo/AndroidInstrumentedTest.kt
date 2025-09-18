package com.my.composedemo

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.my.composedemo", appContext.packageName)
    }
    
    @Test
    fun testAndroidPlatform() {
        val platform = Platform()
        val platformName = platform.name
        assertEquals("Android", platformName)
    }
    
    @Test
    fun testGreetingOnAndroid() {
        val greeting = Greeting().greet()
        assertTrue(greeting.contains("Hello"), "Greeting should contain 'Hello'")
        assertTrue(greeting.contains("Android"), "Greeting should contain 'Android'")
    }
    
    @Test
    fun testMathOperationsOnAndroid() {
        assertEquals(15, 10 + 5)
        assertEquals(50, 10 * 5)
        assertEquals(2, 10 / 5)
    }
}
