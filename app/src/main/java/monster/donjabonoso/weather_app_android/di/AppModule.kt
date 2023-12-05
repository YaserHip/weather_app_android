package monster.donjabonoso.weather_app_android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import monster.donjabonoso.weather_app_android.data.remote.Api
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providerApi():Api{
        return Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/v1")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
}