package monster.donjabonoso.weather_app_android.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import monster.donjabonoso.weather_app_android.domain.location.HelperLocation
import monster.donjabonoso.weather_app_android.domain.repository.RepositoryWeather
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import monster.donjabonoso.weather_app_android.domain.Resource

@HiltViewModel
class ViewModelWeather @Inject constructor(
    private val repository: RepositoryWeather,
    private val helperLocation: HelperLocation
): ViewModel() {
    var state by mutableStateOf(StateWeather())
        private set

    fun getWeather() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)

            helperLocation.getCurrentLocation()?.let { location ->
                state = when(val result = repository.getData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state.copy(isLoading = false, weather = result.data)
                    }

                    is Resource.Error -> {
                        state.copy(isLoading = false, error = result.message)
                    }
                }
            } ?: run {
                state = state.copy(isLoading = false, error = "Permission denied")
            }
        }
    }
}