package monster.donjabonoso.weather_app_android.presentation

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import monster.donjabonoso.weather_app_android.ui.theme.Weather_app_androidTheme
import java.util.Date

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ViewModelWeather by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                if (permissions[android.Manifest.permission.ACCESS_FINE_LOCATION] == true) {
                    viewModel.getWeather()
                }
            }
        permissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        setContent {

            Weather_app_androidTheme {
                Surface(color = MaterialTheme.colorScheme.primary) {
                    Box(Modifier.fillMaxSize()) {
                        if (viewModel.state.isLoading) {
                            CircularProgressIndicator(
                                Modifier.align(Alignment.Center),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        } else {
                            viewModel.state.weather?.let { weather ->
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Spacer(modifier = Modifier.size(16.dp))
                                    Text(
                                        text = weather.name,
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Spacer(modifier = Modifier.size(16.dp))
                                    val sdf = SimpleDateFormat("E, dd MMMM")
                                    val currentDate = sdf.format(Date())
                                    Text(text = currentDate, style = TextStyle(fontSize = 14.sp))
                                    Text(
                                        text = "${weather.temp.toInt()}°",
                                        style = TextStyle(fontSize = 160.sp)
                                    )
                                    Text(
                                        text = "Feels like: ${weather.feelsLike.toInt()}°",
                                        style = TextStyle(fontSize = 14.sp)
                                    )
                                    Spacer(modifier = Modifier.size(8.dp))
                                    Text(
                                        text = "Humidity: ${weather.humidity.toInt()}%",
                                        style = TextStyle(fontSize = 14.sp)
                                    )
                                    Spacer(modifier = Modifier.size(8.dp))
                                    Text(
                                        text = "Wind: ${weather.wind.toInt()} km/h",
                                        style = TextStyle(fontSize = 14.sp)
                                    )
                                }
                            }
                        }
                    }


                }
            }
        }
    }
}