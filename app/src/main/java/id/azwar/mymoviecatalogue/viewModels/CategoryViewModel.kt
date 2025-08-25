package id.azwar.mymoviecatalogue.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.model.Genre
import id.azwar.mymoviecatalogue.domain.usecase.GetMoviesByGenreUseCase
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
class CategoryViewModel @Inject constructor(
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase,
    private val favoriteMovieUseCase: FavoriteMovieUseCase
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private var currentGenre: Genre? = null
    private var currentMovieId: Long = 0L

    fun setGenreAndLoadMovies(genre: Genre) {
        currentGenre = genre
        loadMoviesByGenre(genre)
    }

    private fun loadMoviesByGenre(genre: Genre) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _errorMessage.value = null

                getMoviesByGenreUseCase(genre.id).collect { movies ->
                    _movies.value = movies
                }

            } catch (e: Exception) {
                _errorMessage.value = "Failed to load movies: ${e.message}"
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
                val currentMovie = _movies.value.find { it.id == currentMovieId }
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

    fun getCurrentGenre(): Genre? = currentGenre
}
