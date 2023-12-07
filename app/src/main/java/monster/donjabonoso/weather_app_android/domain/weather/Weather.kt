package monster.donjabonoso.weather_app_android.domain.weather

data class Weather(
    val name: String,
    val icon: String,
    val temp: String,
    val feelsLike: String,
    val humidity: String,
    val wind: String
)