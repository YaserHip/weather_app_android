package monster.donjabonoso.weather_app_android.domain.location

import android.location.Location

interface HelperLocation {
    suspend fun getCurrentLocation(): Location?
}