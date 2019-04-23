package pl.wojtek.koziol.pogoda.data.network.response

import com.google.gson.annotations.SerializedName
import pl.wojtek.koziol.pogoda.data.db.entity.Weather

data class WeatherResponse(
    //to sa adnotacje zeby sobie gson poradzil ew jak chcesz to zmien sobie na moshi czy jacksona ale dziala tak samo moshi jest troszke szybsze
    @SerializedName("main")
    val weather: Weather
)