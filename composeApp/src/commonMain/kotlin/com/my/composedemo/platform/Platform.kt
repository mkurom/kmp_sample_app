package com.my.composedemo.platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

