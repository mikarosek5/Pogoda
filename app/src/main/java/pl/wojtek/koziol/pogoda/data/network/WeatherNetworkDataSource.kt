package pl.wojtek.koziol.pogoda.data.network

import androidx.lifecycle.LiveData
import pl.wojtek.koziol.pogoda.data.db.entity.Weather
import pl.wojtek.koziol.pogoda.data.network.response.WeatherResponse

interface WeatherNetworkDataSource {
    val downloadedWeather:LiveData<WeatherResponse>

    suspend fun fetchWeather(city:String)
}