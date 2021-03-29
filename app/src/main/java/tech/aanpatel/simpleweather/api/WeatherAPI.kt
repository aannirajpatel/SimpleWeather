package tech.aanpatel.simpleweather.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather?units=imperial")
    suspend fun getWeatherData(@Query("q") city:String, @Query("appid") apiKey:String): Weather

    companion object{
        private val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        val BASE_IMAGE_URL = "https://openweathermap.org/img/wn/"
        private fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }
        val weatherAPI: WeatherAPI by lazy {
            getRetrofitInstance().create(WeatherAPI::class.java)
        }
    }
}