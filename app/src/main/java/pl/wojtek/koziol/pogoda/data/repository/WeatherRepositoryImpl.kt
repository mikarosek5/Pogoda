package pl.wojtek.koziol.pogoda.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import pl.wojtek.koziol.pogoda.data.db.WeatherDao
import pl.wojtek.koziol.pogoda.data.db.entity.Weather
import pl.wojtek.koziol.pogoda.data.network.WeatherNetworkDataSource

class WeatherRepositoryImpl(
    private val networkDataSource: WeatherNetworkDataSource,
    private val weatherDao: WeatherDao
) : WeatherRepository {


    init {
        networkDataSource.downloadedWeather.observeForever {
            persistFetcherWeathed(it.weather)
        }
    }

    override suspend fun getWeather(city: String): LiveData<Weather> {
        return withContext(Dispatchers.IO){
            initWeatherData(city)
            return@withContext weatherDao.getWeather()
        }
    }

    private fun persistFetcherWeathed(weather: Weather) {
        GlobalScope.launch(Dispatchers.IO) {
            weatherDao.upsert(weather)
        }
    }

    private suspend fun initWeatherData(city:String) {
        if (isFetchNeded(ZonedDateTime.now().minusHours(4))) //Todo
            fetchWeather(city)
    }
    private suspend fun fetchWeather(city: String){
        networkDataSource.fetchWeather(city)
    }

    private fun isFetchNeded(lastFetchTime: ZonedDateTime): Boolean {
        val twoHoursAgo = ZonedDateTime.now().minusHours(2)
        return lastFetchTime.isBefore(twoHoursAgo)
    }
}