package monster.donjabonoso.weather_app_android.domain.repository

interface RepositoryWeather {
    suspend fun getData(lat: Double, lon: Double)
}