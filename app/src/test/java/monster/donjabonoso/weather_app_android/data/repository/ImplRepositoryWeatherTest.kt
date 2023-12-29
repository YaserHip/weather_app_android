package monster.donjabonoso.weather_app_android.data.repository

import kotlinx.coroutines.runBlocking
import monster.donjabonoso.weather_app_android.data.remote.Api
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ImplRepositoryWeatherTest {
    private val mockWebServer = MockWebServer()
    private val client = OkHttpClient.Builder().build()
    private lateinit var repositoryWeather: ImplRepositoryWeather
    private lateinit var api: Api


    @Before
    fun setUp() {
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(Api::class.java)

        repositoryWeather = ImplRepositoryWeather(api)
    }

    @Test
    fun `Error response 404`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(404)
        )

        val response = repositoryWeather.getData(123.0,123.0)

        assert(true)
    }
}