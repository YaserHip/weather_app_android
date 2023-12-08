package monster.donjabonoso.weather_app_android.data.repository

import monster.donjabonoso.weather_app_android.data.mappers.toWeather
import monster.donjabonoso.weather_app_android.data.remote.Api
import monster.donjabonoso.weather_app_android.domain.Resource
import monster.donjabonoso.weather_app_android.domain.repository.RepositoryWeather
import monster.donjabonoso.weather_app_android.domain.weather.Weather
import javax.inject.Inject

class ImplRepositoryWeather @Inject constructor(
    private val api: Api
): RepositoryWeather{
    override suspend fun getData(lat: Double, lon: Double): Resource<Weather> {
        return try {
            Resource.Success(data = api.getCurrentWeather(latLon = "$lat, $lon").toWeather())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message?:"Unknown error.")
        }
    }
}