package id.azwar.mymoviecatalogue.domain.repository

import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.model.MovieDetails
import id.azwar.mymoviecatalogue.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getTrendingMovies(timeWindow: String): Flow<List<Movie>>
    suspend fun getMovieDetails(movieId: Long): Flow<MovieDetails?>
    suspend fun searchMovies(query: String): Flow<List<Movie>>
    suspend fun getMovieGenres(): Flow<List<Genre>>
    suspend fun getUpcomingMovies(): Flow<List<Movie>>
    suspend fun getMoviesByGenre(genreId: Int): Flow<List<Movie>>
}
