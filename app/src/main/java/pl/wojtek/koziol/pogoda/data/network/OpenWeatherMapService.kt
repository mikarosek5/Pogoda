package pl.wojtek.koziol.pogoda.data.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import pl.wojtek.koziol.pogoda.data.network.response.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY =
    "749561a315b14523a8f5f1ef95e45864"

interface OpenWeatherMapService {


    @GET("data/2.5/weather")
    fun getWeather(@Query("q") city: String): Deferred<WeatherResponse>

    companion object {
        operator fun invoke(): OpenWeatherMapService {
            val apiKeyInterceptor =
                Interceptor {
                    val url = it.request()
                        .url()
                        .newBuilder()
                        .addQueryParameter("APPID", API_KEY)
                        .build()
                    val request = it.request()
                        .newBuilder()
                        .url(url)
                        .build()
                    return@Interceptor it.proceed(request)
                }
            val units =
                Interceptor {
                    val url = it.request()
                        .url()
                        .newBuilder()
                        .addQueryParameter("units", "metric")
                        .build()
                    val request = it.request()
                        .newBuilder()
                        .url(url)
                        .build()
                    return@Interceptor it.proceed(request)
                }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor)
                .addInterceptor(units)
                .build()
            return Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl("http://api.openweathermap.org/")
                .build()
                .create(OpenWeatherMapService::class.java)
        }
    }

}