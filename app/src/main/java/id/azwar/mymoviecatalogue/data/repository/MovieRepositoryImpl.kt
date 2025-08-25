package id.azwar.mymoviecatalogue.data.repository

import id.azwar.mymoviecatalogue.domain.repository.MovieRepository
import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.model.MovieDetails
import id.azwar.mymoviecatalogue.domain.model.Genre
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

    override suspend fun searchMovies(query: String): Flow<List<Movie>> = flow {
        try {
            println("🔍 Repository: Starting search for query: '$query'")
            if (query.isBlank()) {
                println("🔍 Repository: Query is blank, returning empty list")
                emit(emptyList())
                return@flow
            }

            println("🔍 Repository: Calling remoteDataSource.searchMovies with query: '$query'")
            val response = remoteDataSource.searchMovies(query.trim())
            println("🔍 Repository: Received response with ${response.results.size} movies")

            val movies = response.results.mapNotNull { movieDto ->
                try {
                    val movie = mapper.mapMovieDtoToDomain(movieDto)
                    println("🔍 Repository: Successfully mapped movie: ${movie.title}")
                    movie
                } catch (e: Exception) {

                    println("❌ Repository: Error mapping movie ${movieDto.id}: ${e.message}")
                    null
                }
            }
            println("🔍 Repository: Emitting ${movies.size} successfully mapped movies")
            emit(movies)
        } catch (e: Exception) {
            println("❌ Repository: Search error: ${e.message}")
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun getMovieGenres(): Flow<List<Genre>> = flow {
        try {
            val response = remoteDataSource.getMovieGenres()
            val genres = response.genres.map { genreDto ->
                mapper.mapGenreDtoToDomain(genreDto)
            }
            emit(genres)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getUpcomingMovies(): Flow<List<Movie>> = flow {
        try {
            val response = remoteDataSource.getUpcomingMovies()
            val movies = response.results.map { movieDto ->
                mapper.mapMovieDtoToDomain(movieDto)
            }
            emit(movies)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getMoviesByGenre(genreId: Int): Flow<List<Movie>> = flow {
        try {
            val response = remoteDataSource.getMoviesByGenre(genreId)
            val movies = response.results.map { movieDto ->
                mapper.mapMovieDtoToDomain(movieDto)
            }
            emit(movies)
        } catch (e: Exception) {
            throw e
        }
    }
}
