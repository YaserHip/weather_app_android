package monster.donjabonoso.weather_app_android.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import monster.donjabonoso.weather_app_android.data.location.ImplHelperLocation
import monster.donjabonoso.weather_app_android.domain.location.HelperLocation
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ModuleLocation {

    @Binds
    @Singleton
    abstract fun bindHelperLocation(implHelperLocation: ImplHelperLocation): HelperLocation
}