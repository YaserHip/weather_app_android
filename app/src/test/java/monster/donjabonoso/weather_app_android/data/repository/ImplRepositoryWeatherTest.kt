package monster.donjabonoso.weather_app_android.data.repository

import assertk.assertThat
import kotlinx.coroutines.runBlocking
import monster.donjabonoso.weather_app_android.data.remote.Api
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class ImplRepositoryWeatherTest {

    private lateinit var repositoryWeather: ImplRepositoryWeather
    private lateinit var api: Api
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        api = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create()
        repositoryWeather = ImplRepositoryWeather(api)
    }

    @Test
    fun `Error response 404`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(404)
        )

        val response = repositoryWeather.getData(123.0,123.0)

        assert(null == null)
    }


}