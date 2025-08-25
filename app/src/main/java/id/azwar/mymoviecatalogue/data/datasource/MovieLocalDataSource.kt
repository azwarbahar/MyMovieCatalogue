package id.azwar.mymoviecatalogue.data.datasource

import id.azwar.mymoviecatalogue.data.local.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>>
    suspend fun getFavoriteMovieById(movieId: Long): FavoriteMovieEntity?
    suspend fun isMovieFavorite(movieId: Long): Boolean
    suspend fun insertFavoriteMovie(movie: FavoriteMovieEntity)
    suspend fun deleteFavoriteMovie(movie: FavoriteMovieEntity)
    suspend fun deleteFavoriteMovieById(movieId: Long)
    fun getFavoriteMoviesCount(): Flow<Int>
}
