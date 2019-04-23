package pl.wojtek.koziol.pogoda.ui.forecast_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import pl.wojtek.koziol.pogoda.data.repository.WeatherRepository

class ForecastViewModelFactory(private val weatherRepository: WeatherRepository):ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ForecastViewModel(weatherRepository) as T
    }
}