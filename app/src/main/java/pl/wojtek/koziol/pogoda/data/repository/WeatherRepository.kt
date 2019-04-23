package pl.wojtek.koziol.pogoda.data.repository

import androidx.lifecycle.LiveData
import pl.wojtek.koziol.pogoda.data.db.entity.Weather

interface WeatherRepository {

    suspend fun getWeather(city:String):LiveData<Weather>
}