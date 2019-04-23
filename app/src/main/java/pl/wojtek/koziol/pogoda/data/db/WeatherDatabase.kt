package pl.wojtek.koziol.pogoda.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.wojtek.koziol.pogoda.data.db.entity.Weather

@Database(entities = [Weather::class],
    version = 1,
    exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        var instance: WeatherDatabase? = null
        private val LOCK = Any()
        @Synchronized
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, WeatherDatabase::class.java, "weather.db").build()
    }
}