package tech.aanpatel.simpleweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class WeatherViewModel: ViewModel() {
    private var tempF = ""
    var temp = MutableLiveData<String>()

    var switchInfo = MutableLiveData<String>()

    init{
        temp.value="54°F"
        tempF = "54°F"
    }

    fun setTemp(temperature: String){
        temp.value=temperature
        tempF = temperature
    }

    fun toggleSwitch(switchVal: Boolean){
        if(!switchVal){
            val f: Float = tempF.split("°")[0].toFloat()
            temp.value = ((f-32)*5/9).roundToInt().toString()+"°"+"C"
            switchInfo.value = "Show me Fahrenheit"
        } else{
            temp.value = tempF
            switchInfo.value = "Show me Celsius"
        }
    }
}