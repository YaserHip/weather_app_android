package monster.donjabonoso.weather_app_android.data.remote

import com.squareup.moshi.Json

data class WeatherLocationDto(
    val name: String,
    val region: String,
    val country: String,
    val localtime: String
)

data class WeatherConditionDto(
    val text: String,
    val icon: String,
    val code: Int
)

data class WeatherCurrentDto(
    @field:Json(name = "last_updated")
    val lastUpdated: String,
    @field:Json(name = "temp_c")
    val temp: String,
    val condition: WeatherConditionDto,
    val humidity: Double,
    @field:Json(name = "feelslike_c")
    val feelslike: Double,
    @field:Json(name = "wind_kph")
    var wind: Double
)

data class WeatherDto(
    val location: WeatherLocationDto,
    val current: WeatherCurrentDto
)