package monster.donjabonoso.weather_app_android.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import monster.donjabonoso.weather_app_android.data.repository.ImplRepositoryWeather
import monster.donjabonoso.weather_app_android.domain.repository.RepositoryWeather
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ModuleRepository {
    @Binds
    @Singleton
    abstract fun bindRepositoryWeather(
        implRepositoryWeather: ImplRepositoryWeather
    ): RepositoryWeather
}