package id.azwar.mymoviecatalogue.domain.usecase

import id.azwar.mymoviecatalogue.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetTrendingMoviesUseCase {
    suspend operator fun invoke(timeWindow: String): Flow<List<Movie>>
}
