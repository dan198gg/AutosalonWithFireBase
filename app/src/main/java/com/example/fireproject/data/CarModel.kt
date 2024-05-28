package com.example.fireproject.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

data class CarModel(
    var brand:String="",
    var model:String="",
    var stats: Map<String, String> = mapOf(),
    var colors: MutableList<String> = mutableListOf(""),
    var price:String="",
    var imageUrl:String=""
)