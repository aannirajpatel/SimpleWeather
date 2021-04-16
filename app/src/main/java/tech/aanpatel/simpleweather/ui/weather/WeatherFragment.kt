package tech.aanpatel.simpleweather.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import tech.aanpatel.simpleweather.R
import tech.aanpatel.simpleweather.databinding.WeatherFragmentBinding
import java.util.*

class WeatherFragment : Fragment() {

    private var _binding: WeatherFragmentBinding? = null
    private var viewModel: WeatherViewModel? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WeatherFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    val args: WeatherFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //create viewModel and set LifecycleOwner for the binding object
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        _binding!!.setLifecycleOwner { lifecycle }

        // prepare viewModel
        viewModel!!.setTemp(args.temperature)
        viewModel!!.toggleSwitch(true)

        // set the switch listener
        _binding!!.tempUnitSwitch.setOnClickListener {
            viewModel!!.toggleSwitch(!((it as Button).text.split(" ")[2]=="Celsius"))
        }

        // provide viewModel to data binding
        _binding!!.viewmodel = viewModel
        _binding!!.executePendingBindings()

        // load the weather image via Picasso
        Picasso.get().load(args.iconUrl).into(_binding!!.weatherIcon);

        // load other stats
        _binding!!.humidity.text = args.humidity
        _binding!!.pressure.text = args.pressure
        _binding!!.cityName.text = (getString(R.string.cityText) + " " + args.city.capitalize(Locale.ROOT))
        _binding!!.weatherStatus.text = args.weatherType
    }


}