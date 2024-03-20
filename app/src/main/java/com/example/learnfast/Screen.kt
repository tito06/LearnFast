package com.example.learnfast

sealed class Screen(val route:String) {

    object  Greeting:Screen("greeting")
    object  PlayScreen: Screen("playScreen")


}