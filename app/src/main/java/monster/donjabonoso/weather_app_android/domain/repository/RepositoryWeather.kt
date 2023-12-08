package monster.donjabonoso.weather_app_android.domain.repository

import monster.donjabonoso.weather_app_android.domain.Resource
import monster.donjabonoso.weather_app_android.domain.weather.Weather

interface RepositoryWeather {
    suspend fun getData(lat: Double, lon: Double): Resource<Weather>
}