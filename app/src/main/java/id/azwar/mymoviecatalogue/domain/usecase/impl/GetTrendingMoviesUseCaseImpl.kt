package id.azwar.mymoviecatalogue.domain.usecase.impl

import id.azwar.mymoviecatalogue.domain.usecase.GetTrendingMoviesUseCase
import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrendingMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetTrendingMoviesUseCase {
    
    override suspend operator fun invoke(timeWindow: String): Flow<List<Movie>> {
        return movieRepository.getTrendingMovies(timeWindow)
    }
}
