package id.azwar.mymoviecatalogue.domain.usecase.impl

import id.azwar.mymoviecatalogue.domain.usecase.FavoriteMovieUseCase
import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.model.FavoriteMovie
import id.azwar.mymoviecatalogue.domain.repository.FavoriteMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteMovieUseCaseImpl @Inject constructor(
    private val favoriteMovieRepository: FavoriteMovieRepository
) : FavoriteMovieUseCase {
    
    override suspend fun addToFavorites(movie: Movie) {
        favoriteMovieRepository.addToFavorites(movie)
    }
    
    override suspend fun removeFromFavorites(movieId: Long) {
        favoriteMovieRepository.removeFromFavorites(movieId)
    }
    
    override suspend fun toggleFavorite(movie: Movie) {
        favoriteMovieRepository.toggleFavorite(movie)
    }
    
    override suspend fun isMovieFavorite(movieId: Long): Boolean {
        return favoriteMovieRepository.isMovieFavorite(movieId)
    }
    
    override fun getAllFavoriteMovies(): Flow<List<FavoriteMovie>> {
        return favoriteMovieRepository.getAllFavoriteMovies()
    }
    
    override fun getFavoriteMoviesCount(): Flow<Int> {
        return favoriteMovieRepository.getFavoriteMoviesCount()
    }
}
