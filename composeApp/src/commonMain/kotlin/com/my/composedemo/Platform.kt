package com.my.composedemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform