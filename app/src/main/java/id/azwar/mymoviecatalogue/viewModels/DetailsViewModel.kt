package id.azwar.mymoviecatalogue.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.azwar.mymoviecatalogue.domain.model.MovieDetails
import id.azwar.mymoviecatalogue.domain.usecase.GetMovieDetailsUseCase
import id.azwar.mymoviecatalogue.domain.usecase.FavoriteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val favoriteMovieUseCase: FavoriteMovieUseCase
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<MovieDetails?>(null)
    val movieDetails: StateFlow<MovieDetails?> = _movieDetails.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private var currentMovieId: Long = 0L

    val favoriteMovieIds: StateFlow<List<Long>> =
        favoriteMovieUseCase.getAllFavoriteMovies()
            .map { movies -> movies.map { it.id } }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun setMovieIdAndFetch(movieId: Long) {
        currentMovieId = movieId
        fetchMovieDetails(movieId)
    }

    private fun fetchMovieDetails(movieId: Long) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {
                getMovieDetailsUseCase(movieId).collect { details ->
                    _movieDetails.value = details
                }
            } catch (e: Exception) {
                if (e.message?.contains("Job was cancelled") != true) {
                    _errorMessage.value = "Failed to fetch movie details: ${e.localizedMessage}"
                }
                _movieDetails.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            try {
                val currentMovie = _movieDetails.value
                currentMovie?.let { movieDetails ->
                    val movie = id.azwar.mymoviecatalogue.domain.model.Movie(
                        id = movieDetails.id,
                        title = movieDetails.title,
                        originalTitle = movieDetails.originalTitle,
                        overview = movieDetails.overview,
                        posterPath = movieDetails.posterPath,
                        backdropPath = movieDetails.backdropPath,
                        releaseDate = movieDetails.releaseDate,
                        voteAverage = movieDetails.voteAverage,
                        voteCount = movieDetails.voteCount,
                        popularity = movieDetails.popularity,
                        adult = movieDetails.adult,
                        originalLanguage = movieDetails.originalLanguage,
                        mediaType = "movie",
                        video = false,
                        genreIds = emptyList()
                    )
                    favoriteMovieUseCase.toggleFavorite(movie)
                }
            } catch (e: Exception) {
                if (e.message?.contains("Job was cancelled") != true) {
                    _errorMessage.value = "Failed to toggle favorite: ${e.message}"
                }
            }
        }
    }

    fun isMovieFavorite(movieId: Long): Boolean {
        return favoriteMovieIds.value.contains(movieId)
    }
}
