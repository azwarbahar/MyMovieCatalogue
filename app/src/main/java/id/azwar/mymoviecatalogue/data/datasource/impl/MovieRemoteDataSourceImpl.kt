package id.azwar.mymoviecatalogue.data.datasource.impl

import id.azwar.mymoviecatalogue.data.datasource.MovieRemoteDataSource
import id.azwar.mymoviecatalogue.data.model.TMDBResponse
import id.azwar.mymoviecatalogue.data.model.MovieDetailsDto
import id.azwar.mymoviecatalogue.data.model.GenreListResponse
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

    override suspend fun searchMovies(query: String): TMDBResponse {
        try {
            println("🔍 RemoteDataSource: Starting search for query: '$query'")
            if (query.isBlank()) {
                throw IllegalArgumentException("Search query cannot be blank")
            }
            println("🔍 RemoteDataSource: Calling apiService.searchMovies with query: '$query'")
            val response = apiService.searchMovies(query.trim())
            println("🔍 RemoteDataSource: Received response with ${response.results.size} movies")
            return response
        } catch (e: Exception) {
            println("❌ RemoteDataSource: Search error: ${e.message}")
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun getMovieGenres(): GenreListResponse {
        return apiService.getMovieGenres()
    }

    override suspend fun getUpcomingMovies(): TMDBResponse {
        return apiService.getUpcomingMovies()
    }

    override suspend fun getMoviesByGenre(genreId: Int): TMDBResponse {
        return apiService.getMoviesByGenre(genreId)
    }
}
