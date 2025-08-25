package id.azwar.mymoviecatalogue.domain.repository

import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getTrendingMovies(timeWindow: String): Flow<List<Movie>>
    suspend fun getMovieDetails(movieId: Long): Flow<MovieDetails?>
}
