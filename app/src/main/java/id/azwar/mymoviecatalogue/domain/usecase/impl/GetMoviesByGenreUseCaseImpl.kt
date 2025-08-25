package id.azwar.mymoviecatalogue.domain.usecase.impl

import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.repository.MovieRepository
import id.azwar.mymoviecatalogue.domain.usecase.GetMoviesByGenreUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesByGenreUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetMoviesByGenreUseCase {

    override suspend operator fun invoke(genreId: Int): Flow<List<Movie>> {
        return movieRepository.getMoviesByGenre(genreId)
    }
}
