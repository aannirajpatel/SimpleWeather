package tech.aanpatel.simpleweather.api

import com.squareup.moshi.Json

data class WeatherDetails(
    @field:Json(name="icon")
    val iconName: String?=null,
    @field:Json(name="main")
    val weatherType: String?="Unable to get weather type :("
)