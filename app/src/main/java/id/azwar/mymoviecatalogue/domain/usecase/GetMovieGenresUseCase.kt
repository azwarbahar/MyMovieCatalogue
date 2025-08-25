package id.azwar.mymoviecatalogue.domain.usecase

import id.azwar.mymoviecatalogue.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface GetMovieGenresUseCase {
    suspend operator fun invoke(): Flow<List<Genre>>
}
