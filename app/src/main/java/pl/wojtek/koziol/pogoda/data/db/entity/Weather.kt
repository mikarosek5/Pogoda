package pl.wojtek.koziol.pogoda.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val WEATHER_ID = 0
@Entity(tableName = "weather")
data class Weather(
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
){
    @PrimaryKey(autoGenerate = false)
    var id = WEATHER_ID
}