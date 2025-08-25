package id.azwar.mymoviecatalogue.domain.repository

import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.model.FavoriteMovie
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieRepository {
    suspend fun addToFavorites(movie: Movie)
    suspend fun removeFromFavorites(movieId: Long)
    suspend fun toggleFavorite(movie: Movie)
    suspend fun isMovieFavorite(movieId: Long): Boolean
    fun getAllFavoriteMovies(): Flow<List<FavoriteMovie>>
    fun getFavoriteMoviesCount(): Flow<Int>
}
