package id.azwar.mymoviecatalogue.domain.usecase

import id.azwar.mymoviecatalogue.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetUpcomingMoviesUseCase {
    suspend operator fun invoke(): Flow<List<Movie>>
}
