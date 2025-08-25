package id.azwar.mymoviecatalogue.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.azwar.mymoviecatalogue.domain.usecase.GetTrendingMoviesUseCase
import id.azwar.mymoviecatalogue.domain.usecase.GetMovieDetailsUseCase
import id.azwar.mymoviecatalogue.domain.usecase.FavoriteMovieUseCase
import id.azwar.mymoviecatalogue.domain.usecase.impl.GetTrendingMoviesUseCaseImpl
import id.azwar.mymoviecatalogue.domain.usecase.impl.GetMovieDetailsUseCaseImpl
import id.azwar.mymoviecatalogue.domain.usecase.impl.FavoriteMovieUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    
    @Binds
    @Singleton
    abstract fun bindGetTrendingMoviesUseCase(
        getTrendingMoviesUseCaseImpl: GetTrendingMoviesUseCaseImpl
    ): GetTrendingMoviesUseCase
    
    @Binds
    @Singleton
    abstract fun bindGetMovieDetailsUseCase(
        getMovieDetailsUseCaseImpl: GetMovieDetailsUseCaseImpl
    ): GetMovieDetailsUseCase
    
    @Binds
    @Singleton
    abstract fun bindFavoriteMovieUseCase(
        favoriteMovieUseCaseImpl: FavoriteMovieUseCaseImpl
    ): FavoriteMovieUseCase
}
