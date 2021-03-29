package com.example.simpleweather.ui.Search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.simpleweather.R
import com.example.simpleweather.api.WeatherAPI
import com.example.simpleweather.databinding.FragmentSearchBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        _binding!!.weatherDataGetProgress.visibility = View.GONE
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weatherAPI = WeatherAPI.weatherAPI
        val viewModelFactory = SearchViewModelFactory(weatherAPI)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)

        _binding!!.getWeatherBtn.setOnClickListener {
            _binding!!.weatherDataGetProgress.visibility = View.VISIBLE
            viewModel.getWeatherData(
                _binding!!.locationEditText.text.toString(),
                getString(R.string.api_key)
            )
        }
        viewModel.weatherGetSuccess.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToWeatherFragment(
                        temperature = viewModel.temp.value!!,
                        humidity = viewModel.humidity.value!!,
                        pressure = viewModel.pressure.value!!,
                        city=_binding!!.locationEditText.text.toString(),
                        iconUrl = viewModel.icon.value!!,
                        weatherType=viewModel.details.value!!
                    )
                )
                viewModel.eventWeatherGetSuccessFinish()
            }else{
                _binding!!.weatherDataGetProgress.visibility = View.GONE
            }
        })
        viewModel.weatherGetFailure.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(
                    context,
                    "Error fetching weather data. Please try again.",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.eventWeatherGetFailureFinish()
            } else{
                _binding!!.weatherDataGetProgress.visibility = View.GONE
            }
        })
    }


}