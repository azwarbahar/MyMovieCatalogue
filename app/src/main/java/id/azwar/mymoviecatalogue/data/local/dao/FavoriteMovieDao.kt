package id.azwar.mymoviecatalogue.data.local.dao

import androidx.room.*
import id.azwar.mymoviecatalogue.data.local.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM favorite_movies ORDER BY addedAt DESC")
    fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId")
    suspend fun getFavoriteMovieById(movieId: Long): FavoriteMovieEntity?

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_movies WHERE id = :movieId)")
    suspend fun isMovieFavorite(movieId: Long): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movie: FavoriteMovieEntity)

    @Delete
    suspend fun deleteFavoriteMovie(movie: FavoriteMovieEntity)

    @Query("DELETE FROM favorite_movies WHERE id = :movieId")
    suspend fun deleteFavoriteMovieById(movieId: Long)

    @Query("SELECT COUNT(*) FROM favorite_movies")
    fun getFavoriteMoviesCount(): Flow<Int>
}
