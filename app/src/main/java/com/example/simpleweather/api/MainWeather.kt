package com.example.simpleweather.api

import com.squareup.moshi.Json

data class MainWeather(
    @field:Json(name = "temp")
    val temp: Float,
    @field:Json(name="pressure")
    val pressure: Float,
    @field:Json(name="humidity")
    val humidity: Float
)
