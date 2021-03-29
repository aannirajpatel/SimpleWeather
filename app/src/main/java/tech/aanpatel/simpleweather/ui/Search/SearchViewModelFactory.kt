package tech.aanpatel.simpleweather.ui.Search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tech.aanpatel.simpleweather.api.WeatherAPI

class SearchViewModelFactory(val weatherAPI: WeatherAPI): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(weatherAPI) as T
    }

}