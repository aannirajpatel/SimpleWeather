package com.example.simpleweather.ui.Search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleweather.api.WeatherAPI

class SearchViewModelFactory(val weatherAPI: WeatherAPI): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(weatherAPI) as T
    }

}