package pl.wojtek.koziol.pogoda

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import pl.wojtek.koziol.pogoda.data.db.WeatherDatabase
import pl.wojtek.koziol.pogoda.data.network.OpenWeatherMapService
import pl.wojtek.koziol.pogoda.data.network.WeatherNetworkDataSource
import pl.wojtek.koziol.pogoda.data.network.WeatherNetworkDataSourceImpl
import pl.wojtek.koziol.pogoda.data.repository.WeatherRepository
import pl.wojtek.koziol.pogoda.data.repository.WeatherRepositoryImpl
import pl.wojtek.koziol.pogoda.ui.forecast_screen.ForecastViewModelFactory

class ForecastApp:Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApp)) //tutaj tez sa bindy ale dostarczone razem z biblioteka wiec nie trzeba np context'u bindowac

        bind() from singleton { WeatherDatabase(instance()) }
        bind() from singleton { instance<WeatherDatabase>().weatherDao() }
        bind() from singleton { OpenWeatherMapService() }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<WeatherRepository>() with singleton { WeatherRepositoryImpl(instance(),instance()) }
        bind() from provider { ForecastViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}