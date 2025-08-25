package id.azwar.mymoviecatalogue.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.azwar.mymoviecatalogue.data.datasource.MovieRemoteDataSource
import id.azwar.mymoviecatalogue.data.datasource.MovieLocalDataSource
import id.azwar.mymoviecatalogue.data.datasource.impl.MovieRemoteDataSourceImpl
import id.azwar.mymoviecatalogue.data.datasource.impl.MovieLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    
    @Binds
    @Singleton
    abstract fun bindMovieRemoteDataSource(
        movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl
    ): MovieRemoteDataSource
    
    @Binds
    @Singleton
    abstract fun bindMovieLocalDataSource(
        movieLocalDataSourceImpl: MovieLocalDataSourceImpl
    ): MovieLocalDataSource
}
