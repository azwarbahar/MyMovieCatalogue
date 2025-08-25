package id.azwar.mymoviecatalogue.data.datasource

import id.azwar.mymoviecatalogue.data.model.TMDBResponse
import id.azwar.mymoviecatalogue.data.model.MovieDetailsDto

interface MovieRemoteDataSource {
    suspend fun getTrendingMovies(timeWindow: String): TMDBResponse
    suspend fun getMovieDetails(movieId: Long): MovieDetailsDto
}
