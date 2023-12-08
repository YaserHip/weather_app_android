package monster.donjabonoso.weather_app_android.domain.weather

data class Weather(
    val name: String,
    val icon: String,
    val temp: Double,
    val feelsLike: Double,
    val humidity: Double,
    val wind: Double
)