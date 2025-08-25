package id.azwar.mymoviecatalogue.data.datasource

import id.azwar.mymoviecatalogue.data.model.TMDBResponse
import id.azwar.mymoviecatalogue.data.model.MovieDetailsDto
import id.azwar.mymoviecatalogue.data.model.GenreListResponse

interface MovieRemoteDataSource {
    suspend fun getTrendingMovies(timeWindow: String): TMDBResponse
    suspend fun getMovieDetails(movieId: Long): MovieDetailsDto
    suspend fun searchMovies(query: String): TMDBResponse
    suspend fun getMovieGenres(): GenreListResponse
    suspend fun getUpcomingMovies(): TMDBResponse
    suspend fun getMoviesByGenre(genreId: Int): TMDBResponse
}
