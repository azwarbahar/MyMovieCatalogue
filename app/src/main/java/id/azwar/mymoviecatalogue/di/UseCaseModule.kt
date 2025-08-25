package id.azwar.mymoviecatalogue.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.azwar.mymoviecatalogue.domain.usecase.FavoriteMovieUseCase
import id.azwar.mymoviecatalogue.domain.usecase.GetMovieDetailsUseCase
import id.azwar.mymoviecatalogue.domain.usecase.GetTrendingMoviesUseCase
import id.azwar.mymoviecatalogue.domain.usecase.SearchMoviesUseCase
import id.azwar.mymoviecatalogue.domain.usecase.GetMovieGenresUseCase
import id.azwar.mymoviecatalogue.domain.usecase.GetUpcomingMoviesUseCase
import id.azwar.mymoviecatalogue.domain.usecase.GetMoviesByGenreUseCase
import id.azwar.mymoviecatalogue.domain.usecase.impl.FavoriteMovieUseCaseImpl
import id.azwar.mymoviecatalogue.domain.usecase.impl.GetMovieDetailsUseCaseImpl
import id.azwar.mymoviecatalogue.domain.usecase.impl.GetTrendingMoviesUseCaseImpl
import id.azwar.mymoviecatalogue.domain.usecase.impl.SearchMoviesUseCaseImpl
import id.azwar.mymoviecatalogue.domain.usecase.impl.GetMovieGenresUseCaseImpl
import id.azwar.mymoviecatalogue.domain.usecase.impl.GetUpcomingMoviesUseCaseImpl
import id.azwar.mymoviecatalogue.domain.usecase.impl.GetMoviesByGenreUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetTrendingMoviesUseCase(
        getTrendingMoviesUseCaseImpl: GetTrendingMoviesUseCaseImpl
    ): GetTrendingMoviesUseCase

    @Binds
    abstract fun bindGetMovieDetailsUseCase(
        getMovieDetailsUseCaseImpl: GetMovieDetailsUseCaseImpl
    ): GetMovieDetailsUseCase

    @Binds
    abstract fun bindSearchMoviesUseCase(
        searchMoviesUseCaseImpl: SearchMoviesUseCaseImpl
    ): SearchMoviesUseCase

    @Binds
    abstract fun bindFavoriteMovieUseCase(
        favoriteMovieUseCaseImpl: FavoriteMovieUseCaseImpl
    ): FavoriteMovieUseCase

    @Binds
    abstract fun bindGetMovieGenresUseCase(
        getMovieGenresUseCaseImpl: GetMovieGenresUseCaseImpl
    ): GetMovieGenresUseCase

    @Binds
    abstract fun bindGetUpcomingMoviesUseCase(
        getUpcomingMoviesUseCaseImpl: GetUpcomingMoviesUseCaseImpl
    ): GetUpcomingMoviesUseCase

    @Binds
    abstract fun bindGetMoviesByGenreUseCase(
        getMoviesByGenreUseCaseImpl: GetMoviesByGenreUseCaseImpl
    ): GetMoviesByGenreUseCase
}
