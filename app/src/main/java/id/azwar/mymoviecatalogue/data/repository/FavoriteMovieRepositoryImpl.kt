package id.azwar.mymoviecatalogue.data.repository

import id.azwar.mymoviecatalogue.domain.repository.FavoriteMovieRepository
import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.model.FavoriteMovie
import id.azwar.mymoviecatalogue.data.datasource.MovieLocalDataSource
import id.azwar.mymoviecatalogue.data.mapper.MovieMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteMovieRepositoryImpl @Inject constructor(
    private val localDataSource: MovieLocalDataSource,
    private val mapper: MovieMapper
) : FavoriteMovieRepository {

    override suspend fun addToFavorites(movie: Movie) {
        val entity = mapper.mapDomainToFavoriteMovieEntity(movie)
        localDataSource.insertFavoriteMovie(entity)
    }

    override suspend fun removeFromFavorites(movieId: Long) {
        localDataSource.deleteFavoriteMovieById(movieId)
    }

    override suspend fun toggleFavorite(movie: Movie) {
        val isFavorite = isMovieFavorite(movie.id)
        if (isFavorite) {
            removeFromFavorites(movie.id)
        } else {
            addToFavorites(movie)
        }
    }

    override suspend fun isMovieFavorite(movieId: Long): Boolean {
        return localDataSource.isMovieFavorite(movieId)
    }

    override fun getAllFavoriteMovies(): Flow<List<FavoriteMovie>> {
        return localDataSource.getAllFavoriteMovies().map { entities ->
            entities.map { entity ->
                mapper.mapFavoriteMovieEntityToDomain(entity)
            }
        }
    }

    override fun getFavoriteMoviesCount(): Flow<Int> {
        return localDataSource.getFavoriteMoviesCount()
    }
}
