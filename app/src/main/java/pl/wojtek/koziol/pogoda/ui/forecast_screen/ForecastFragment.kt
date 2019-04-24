package pl.wojtek.koziol.pogoda.ui.forecast_screen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.forecast_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

import pl.wojtek.koziol.pogoda.R
import pl.wojtek.koziol.pogoda.ui.base.ScopedFragment

class ForecastFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val viewModelFactory: ForecastViewModelFactory by instance()
    private lateinit var viewModel: ForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forecast_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ForecastViewModel::class.java)
        // TODO: Use the ViewModel
        arguments?.let {
            val safeArgs = ForecastFragmentArgs.fromBundle(it)
//            safeArgs.cityName
            launch {
                city.text = safeArgs.cityName
                setupForecat(safeArgs.cityName)
            }
        }
    }

    private suspend fun setupForecat(city: String) {
        viewModel.getWeather(city).value.await().observe(this, Observer {
            //            Log.d("aaaaaaaaaaaaa",it.toString())
            if (it == null)
                return@Observer
            group_loading.visibility = View.GONE
            temp.text = getString(R.string.current_temp, it.temp)
            temp_max.text = getString(R.string.max_temp, it.tempMax)
            temp_min.text = getString(R.string.min_temp, it.tempMin)
            humidity.text = getString(R.string.humidity, it.humidity)
            pressure.text = getString(R.string.pressure, it.pressure)
//            Log.d("aaaaaaaaaaaaa",it.toString())
        }
        )
    }

}
