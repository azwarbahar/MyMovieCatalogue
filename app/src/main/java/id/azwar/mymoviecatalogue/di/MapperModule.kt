package id.azwar.mymoviecatalogue.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.azwar.mymoviecatalogue.data.mapper.MovieMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideMovieMapper(): MovieMapper {
        return MovieMapper()
    }
}
