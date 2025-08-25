package id.azwar.mymoviecatalogue.domain.usecase.impl

import id.azwar.mymoviecatalogue.domain.usecase.GetMovieDetailsUseCase
import id.azwar.mymoviecatalogue.domain.model.MovieDetails
import id.azwar.mymoviecatalogue.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetMovieDetailsUseCase {

    override suspend operator fun invoke(movieId: Long): Flow<MovieDetails?> {
        return movieRepository.getMovieDetails(movieId)
    }
}
