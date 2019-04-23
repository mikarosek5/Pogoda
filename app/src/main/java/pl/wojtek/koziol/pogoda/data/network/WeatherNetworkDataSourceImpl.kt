package pl.wojtek.koziol.pogoda.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.wojtek.koziol.pogoda.data.db.entity.Weather
import pl.wojtek.koziol.pogoda.data.network.response.WeatherResponse
import java.lang.Exception

class WeatherNetworkDataSourceImpl(private val weatherMapService: OpenWeatherMapService) : WeatherNetworkDataSource {

    private val _downloadedWeather = MutableLiveData<WeatherResponse>()

    override val downloadedWeather: LiveData<WeatherResponse>
        get() = _downloadedWeather

    override suspend fun fetchWeather(city: String) {
        try {
            val fetchedWeather = weatherMapService.getWeather(city).await()
            _downloadedWeather.postValue(fetchedWeather)
        }catch (e: Exception){ //Todo zrobic odpowiedni Interceptor
            Log.e("Connectivity","No internet connection.", e)
        }
    }
}