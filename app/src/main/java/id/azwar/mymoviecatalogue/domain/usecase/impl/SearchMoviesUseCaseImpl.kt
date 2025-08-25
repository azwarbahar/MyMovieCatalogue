package id.azwar.mymoviecatalogue.domain.usecase.impl

import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.repository.MovieRepository
import id.azwar.mymoviecatalogue.domain.usecase.SearchMoviesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : SearchMoviesUseCase {
    
    override suspend operator fun invoke(query: String): Flow<List<Movie>> {
        return movieRepository.searchMovies(query)
    }
}
