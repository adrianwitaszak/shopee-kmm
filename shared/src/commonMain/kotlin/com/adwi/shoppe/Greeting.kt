package com.adwi.shoppe

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}