package pl.wojtek.koziol.pogoda.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.wojtek.koziol.pogoda.data.db.entity.WEATHER_ID
import pl.wojtek.koziol.pogoda.data.db.entity.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weather: Weather)

    @Query("select * from weather where id = $WEATHER_ID")
    fun getWeather():LiveData<Weather>
}