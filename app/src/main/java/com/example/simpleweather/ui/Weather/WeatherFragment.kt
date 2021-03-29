package com.example.simpleweather.ui.Weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.simpleweather.R
import com.example.simpleweather.databinding.WeatherFragmentBinding
import com.squareup.picasso.Picasso
import java.util.*

class WeatherFragment : Fragment() {

    private var _binding: WeatherFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WeatherFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    val args: WeatherFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        Picasso.get().load(args.iconUrl).into(_binding!!.weatherIcon);


        _binding!!.temperature.text = (args.temperature+"F")
        _binding!!.humidity.text = args.humidity
        _binding!!.pressure.text = args.pressure
        _binding!!.cityName.text = (getString(R.string.cityText) + " " + args.city.capitalize(Locale.ROOT))
        _binding!!.weatherStatus.text = args.weatherType
    }


}