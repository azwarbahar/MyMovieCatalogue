package id.azwar.mymoviecatalogue.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.model.MovieDetails
import id.azwar.mymoviecatalogue.domain.usecase.GetTrendingMoviesUseCase
import id.azwar.mymoviecatalogue.domain.usecase.GetMovieDetailsUseCase
import id.azwar.mymoviecatalogue.domain.usecase.FavoriteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val favoriteMovieUseCase: FavoriteMovieUseCase
) : ViewModel() {
    
    private val _trendingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val trendingMovies: StateFlow<List<Movie>> = _trendingMovies.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _movieDetails = MutableStateFlow<MovieDetails?>(null)
    val movieDetails: StateFlow<MovieDetails?> = _movieDetails.asStateFlow()

    private var currentMovieId: Long = 0L

    init {
        fetchTrendingMovies()
    }

    fun fetchTrendingMovies(timeWindow: String = "day") {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {
                getTrendingMoviesUseCase(timeWindow).collect { movies ->
                    _trendingMovies.value = movies
                }
            } catch (e: Exception) {
                if (e.message?.contains("Job was cancelled") != true) {
                    _errorMessage.value = "Failed to fetch movies: ${e.localizedMessage}"
                    _trendingMovies.value = emptyList()
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchMovieDetails(movieId: Long) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {
                getMovieDetailsUseCase(movieId).collect { details ->
                    _movieDetails.value = details
                }
            } catch (e: Exception) {
                if (e.message?.contains("Job was cancelled") != true) {
                    _errorMessage.value = "Failed to fetch movies: ${e.localizedMessage}"
                    _movieDetails.value = null
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun setMovieId(movieId: Long) {
        currentMovieId = movieId
    }

    val favoriteMovieIds: StateFlow<List<Long>> =
        favoriteMovieUseCase.getAllFavoriteMovies()
            .map { movies -> movies.map { it.id } }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun toggleFavorite() {
        viewModelScope.launch {
            try {
                val currentMovie = _trendingMovies.value.find { it.id == currentMovieId }
                currentMovie?.let { movie ->
                    favoriteMovieUseCase.toggleFavorite(movie)
                }
            } catch (e: Exception) {
                _errorMessage.value = "Failed to toggle favorite: ${e.message}"
            }
        }
    }

    fun isMovieFavorite(movieId: Long): Boolean {
        return favoriteMovieIds.value.contains(movieId)
    }
}