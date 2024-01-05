package monster.donjabonoso.weather_app_android.data.repository

import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import monster.donjabonoso.weather_app_android.data.remote.Api
import monster.donjabonoso.weather_app_android.data.remote.WeatherConditionDto
import monster.donjabonoso.weather_app_android.data.remote.WeatherCurrentDto
import monster.donjabonoso.weather_app_android.data.remote.WeatherDto
import monster.donjabonoso.weather_app_android.data.remote.WeatherLocationDto
import monster.donjabonoso.weather_app_android.domain.Resource
import monster.donjabonoso.weather_app_android.domain.weather.Weather
import org.junit.Test


class ImplRepositoryWeatherTest {
    private val mockApi = mockk<Api>()
    private val repository = ImplRepositoryWeather(mockApi)

    @Test
    fun `test getData with successful response`() = runBlocking {
        // Mocked data
        val mockLatitude = 40.0
        val mockLongitude = -73.0

        // Mocked WeatherDto
        val mockWeatherLocationDto = WeatherLocationDto("City", "Region", "Country", "LocalTime")
        val mockWeatherConditionDto = WeatherConditionDto("Text", "Icon", 200)
        val mockWeatherCurrentDto = WeatherCurrentDto("2023-01-01", 25.0, mockWeatherConditionDto, 70.0, 23.0, 5.0)
        val mockWeatherDto = WeatherDto(mockWeatherLocationDto, mockWeatherCurrentDto)

        // Stubbing the behavior of the mocked API getCurrentWeather function
        coEvery { mockApi.getCurrentWeather(any(), any()) } returns mockWeatherDto

        // Call the method being tested
        val result = repository.getData(mockLatitude, mockLongitude)

        // Verify the result
        val expectedWeather = Weather("City", "Icon", 25.0, 23.0, 70.0, 5.0)
        assertTrue(result is Resource.Success)
        assertEquals((result as Resource.Success).data, expectedWeather)
    }

    @Test
    fun `test getData with exception`() = runBlocking {
        // Mocked data
        val mockLatitude = 40.0
        val mockLongitude = -73.0
        val errorMessage = "Test error message"
        val exception = Exception(errorMessage)

        // Stubbing the behavior of the mocked API getCurrentWeather function to throw an exception
        coEvery { mockApi.getCurrentWeather(any(),any()) } throws exception

        // Call the method being tested
        val result = repository.getData(mockLatitude, mockLongitude)

        // Verify the result
        assertTrue(result is Resource.Error)
        assertEquals((result as Resource.Error).message, errorMessage)
    }
}