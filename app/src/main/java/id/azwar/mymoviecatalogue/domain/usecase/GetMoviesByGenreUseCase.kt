package id.azwar.mymoviecatalogue.domain.usecase

import id.azwar.mymoviecatalogue.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetMoviesByGenreUseCase {
    suspend operator fun invoke(genreId: Int): Flow<List<Movie>>
}
