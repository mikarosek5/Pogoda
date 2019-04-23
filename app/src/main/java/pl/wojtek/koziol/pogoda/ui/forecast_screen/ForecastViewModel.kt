package pl.wojtek.koziol.pogoda.ui.forecast_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import kotlinx.coroutines.Deferred
import pl.wojtek.koziol.pogoda.data.db.entity.Weather
import pl.wojtek.koziol.pogoda.data.repository.WeatherRepository
import pl.wojtek.koziol.pogoda.internal.lazyDeferred

class ForecastViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    suspend fun getWeather(city:String):Lazy<Deferred<LiveData<Weather>>>{
        return lazyDeferred { weatherRepository.getWeather(city) }
    }
}
