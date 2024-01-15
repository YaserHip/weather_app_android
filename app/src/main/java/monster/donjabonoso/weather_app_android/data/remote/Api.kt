package monster.donjabonoso.weather_app_android.data.remote

import monster.donjabonoso.weather_app_android.data.ApiKey
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("current.json?aqi=no")
    suspend fun getCurrentWeather(
        @Query("key") apikey : String = ApiKey.weatherApiKey,
        @Query("q") latLon : String
    ) : WeatherDto
}