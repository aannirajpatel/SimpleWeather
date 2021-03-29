package tech.aanpatel.simpleweather.api

import com.squareup.moshi.Json


data class Weather(
    @field:Json(name="main")
 val main: MainWeather?= null,
    @field:Json(name="name")
 val name: String ?=null,
    @field:Json(name="weather")
 val detailsList: List<WeatherDetails> ?=null
)
