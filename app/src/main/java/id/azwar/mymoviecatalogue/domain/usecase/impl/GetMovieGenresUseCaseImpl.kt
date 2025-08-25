package id.azwar.mymoviecatalogue.domain.usecase.impl

import id.azwar.mymoviecatalogue.domain.model.Genre
import id.azwar.mymoviecatalogue.domain.repository.MovieRepository
import id.azwar.mymoviecatalogue.domain.usecase.GetMovieGenresUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieGenresUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetMovieGenresUseCase {

    override suspend operator fun invoke(): Flow<List<Genre>> {
        return movieRepository.getMovieGenres()
    }
}
