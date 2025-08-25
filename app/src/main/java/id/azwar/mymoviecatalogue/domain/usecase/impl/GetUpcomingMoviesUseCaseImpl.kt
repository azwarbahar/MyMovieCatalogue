package id.azwar.mymoviecatalogue.domain.usecase.impl

import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.repository.MovieRepository
import id.azwar.mymoviecatalogue.domain.usecase.GetUpcomingMoviesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUpcomingMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetUpcomingMoviesUseCase {

    override suspend operator fun invoke(): Flow<List<Movie>> {
        return movieRepository.getUpcomingMovies()
    }
}
