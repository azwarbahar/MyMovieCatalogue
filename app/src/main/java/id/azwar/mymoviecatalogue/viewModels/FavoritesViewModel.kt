package id.azwar.mymoviecatalogue.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.azwar.mymoviecatalogue.domain.model.FavoriteMovie
import id.azwar.mymoviecatalogue.domain.usecase.FavoriteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoriteMovieUseCase: FavoriteMovieUseCase
) : ViewModel() {

    private val _favoriteMovies = MutableStateFlow<List<FavoriteMovie>>(emptyList())
    val favoriteMovies: StateFlow<List<FavoriteMovie>> = _favoriteMovies.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadFavoriteMovies()
    }

    private fun loadFavoriteMovies() {
        viewModelScope.launch {
            favoriteMovieUseCase.getAllFavoriteMovies()
                .onStart {
                    _isLoading.value = true
                    _errorMessage.value = null
                }
                .catch { e ->
                    _errorMessage.value = "Failed to load favorites: ${e.message}"
                    _isLoading.value = false
                }
                .collect { movies ->
                    _favoriteMovies.value = movies
                    _isLoading.value = false
                    _errorMessage.value = null
                }
        }
    }

    fun removeFavorite(movieId: Long) {
        viewModelScope.launch {
            try {
                favoriteMovieUseCase.removeFromFavorites(movieId)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to remove from favorites: ${e.message}"
            }
        }
    }

    fun getFavoriteCount() = favoriteMovieUseCase.getFavoriteMoviesCount()
}