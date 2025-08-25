package id.azwar.mymoviecatalogue.domain.usecase

import id.azwar.mymoviecatalogue.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface SearchMoviesUseCase {
    suspend operator fun invoke(query: String): Flow<List<Movie>>
}
