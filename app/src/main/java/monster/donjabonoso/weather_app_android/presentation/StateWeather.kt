package monster.donjabonoso.weather_app_android.presentation

import monster.donjabonoso.weather_app_android.domain.weather.Weather

data class StateWeather(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val error: String? = null
)