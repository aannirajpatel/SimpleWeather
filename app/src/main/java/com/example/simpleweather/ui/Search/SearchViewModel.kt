package com.example.simpleweather.ui.Search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleweather.api.WeatherAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

class SearchViewModel(private val weatherAPI: WeatherAPI): ViewModel() {
    private var _weatherGetSuccess = MutableLiveData<Boolean>()
    val weatherGetSuccess: LiveData<Boolean> get() = _weatherGetSuccess

    private var _weatherGetFailure = MutableLiveData<Boolean>()
    val weatherGetFailure: LiveData<Boolean> get() = _weatherGetFailure

    private var _temp = MutableLiveData<String>()
    val temp: LiveData<String> get() = _temp

    private var _humidity = MutableLiveData<String>()
    val humidity: LiveData<String> get() = _humidity

    private var _pressure = MutableLiveData<String>()
    val pressure: LiveData<String> get() = _pressure

    private var _icon = MutableLiveData<String>()
    val icon: LiveData<String> get() = _icon

    private var _details = MutableLiveData<String>()
    val details: LiveData<String> get() = _details

    fun getWeatherData(city:String, apiKey: String){
        viewModelScope.launch {
            try {
                val weatherData = withContext(Dispatchers.IO) {
                    weatherAPI.getWeatherData(city, apiKey)
                }
                _temp.value = weatherData.main!!.temp.roundToInt().toString() + "°"
                _humidity.value = weatherData.main!!.humidity.roundToInt().toString() + "%"
                _pressure.value = weatherData.main!!.pressure.roundToInt().toString() +" hPa"
                _icon.value = WeatherAPI.BASE_IMAGE_URL + weatherData.detailsList?.get(0)?.iconName!!+"@4x.png"
                _details.value = weatherData.detailsList?.get(0)?.weatherType!!
                _weatherGetSuccess.value=true
            } catch(e:Exception){
                Log.i("APP_ERROR",e.message.toString());
                _weatherGetFailure.value=true
            }
        }
    }

    fun eventWeatherGetFailureFinish(){
        _weatherGetFailure.value=false
    }

    fun eventWeatherGetSuccessFinish(){
        _weatherGetSuccess.value=false
    }

}