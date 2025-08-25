package id.azwar.mymoviecatalogue.data.datasource.impl

import id.azwar.mymoviecatalogue.data.datasource.MovieLocalDataSource
import id.azwar.mymoviecatalogue.data.local.dao.FavoriteMovieDao
import id.azwar.mymoviecatalogue.data.local.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao
) : MovieLocalDataSource {
    
    override fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>> {
        return favoriteMovieDao.getAllFavoriteMovies()
    }
    
    override suspend fun getFavoriteMovieById(movieId: Long): FavoriteMovieEntity? {
        return favoriteMovieDao.getFavoriteMovieById(movieId)
    }
    
    override suspend fun isMovieFavorite(movieId: Long): Boolean {
        return favoriteMovieDao.isMovieFavorite(movieId)
    }
    
    override suspend fun insertFavoriteMovie(movie: FavoriteMovieEntity) {
        favoriteMovieDao.insertFavoriteMovie(movie)
    }
    
    override suspend fun deleteFavoriteMovie(movie: FavoriteMovieEntity) {
        favoriteMovieDao.deleteFavoriteMovie(movie)
    }
    
    override suspend fun deleteFavoriteMovieById(movieId: Long) {
        favoriteMovieDao.deleteFavoriteMovieById(movieId)
    }
    
    override fun getFavoriteMoviesCount(): Flow<Int> {
        return favoriteMovieDao.getFavoriteMoviesCount()
    }
}
