package id.azwar.mymoviecatalogue.data.repository

import id.azwar.mymoviecatalogue.domain.repository.MovieRepository
import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.model.MovieDetails
import id.azwar.mymoviecatalogue.data.datasource.MovieRemoteDataSource
import id.azwar.mymoviecatalogue.data.mapper.MovieMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val mapper: MovieMapper
) : MovieRepository {
    
    override suspend fun getTrendingMovies(timeWindow: String): Flow<List<Movie>> = flow {
        try {
            val response = remoteDataSource.getTrendingMovies(timeWindow)
            val movies = response.results.map { movieDto ->
                mapper.mapMovieDtoToDomain(movieDto)
            }
            emit(movies)
        } catch (e: Exception) {
            throw e
        }
    }
    
    override suspend fun getMovieDetails(movieId: Long): Flow<MovieDetails?> = flow {
        try {
            val movieDetailsDto = remoteDataSource.getMovieDetails(movieId)
            val movieDetails = mapper.mapMovieDetailsDtoToDomain(movieDetailsDto)
            emit(movieDetails)
        } catch (e: Exception) {
            throw e
        }
    }
}
