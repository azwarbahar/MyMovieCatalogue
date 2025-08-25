package id.azwar.mymoviecatalogue.data.datasource.impl

import id.azwar.mymoviecatalogue.data.datasource.MovieRemoteDataSource
import id.azwar.mymoviecatalogue.data.model.TMDBResponse
import id.azwar.mymoviecatalogue.data.model.MovieDetailsDto
import id.azwar.mymoviecatalogue.data.remote.TMDBApiService
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val apiService: TMDBApiService
) : MovieRemoteDataSource {
    
    override suspend fun getTrendingMovies(timeWindow: String): TMDBResponse {
        return apiService.getTrendingMovies(timeWindow)
    }
    
    override suspend fun getMovieDetails(movieId: Long): MovieDetailsDto {
        return apiService.getMovieDetails(movieId)
    }
}
