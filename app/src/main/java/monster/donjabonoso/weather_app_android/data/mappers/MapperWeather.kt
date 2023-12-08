package monster.donjabonoso.weather_app_android.data.mappers

import monster.donjabonoso.weather_app_android.data.remote.WeatherDto
import monster.donjabonoso.weather_app_android.domain.weather.Weather

fun WeatherDto.toWeather(): Weather {
    return Weather(
        name = location.name,
        icon = current.condition.icon,
        temp = current.temp,
        feelsLike = current.feelslike,
        humidity = current.humidity,
        wind = current.wind
    )
}