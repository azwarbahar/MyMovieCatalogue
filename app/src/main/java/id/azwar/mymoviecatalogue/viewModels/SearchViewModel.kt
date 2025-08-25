package id.azwar.mymoviecatalogue.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.usecase.SearchMoviesUseCase
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
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val favoriteMovieUseCase: FavoriteMovieUseCase
) : ViewModel() {
    
    private val _searchResults = MutableStateFlow<List<Movie>>(emptyList())
    val searchResults: StateFlow<List<Movie>> = _searchResults.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private var currentMovieId: Long = 0L

    fun searchMovies(query: String) {
        if (query.isBlank() || query.length < 2) return
        
        println("🔍 SearchViewModel: Starting search for query: '$query'")
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {
                println("🔍 SearchViewModel: Calling searchMoviesUseCase with query: '$query'")
                searchMoviesUseCase(query.trim()).collect { movies ->
                    println("🔍 SearchViewModel: Received ${movies.size} movies from use case")
                    _searchResults.value = movies
                }
            } catch (e: Exception) {
                println("❌ SearchViewModel: Error occurred: ${e.message}")
                e.printStackTrace()
                if (e.message?.contains("Job was cancelled") != true) {
                    val errorMsg = e.localizedMessage ?: e.message ?: "Unknown error occurred"
                    _errorMessage.value = "Failed to search movies: $errorMsg"
                    _searchResults.value = emptyList()
                }
            } finally {
                _isLoading.value = false
                println("🔍 SearchViewModel: Search completed, isLoading set to false")
            }
        }
    }

    fun clearSearch() {
        _searchResults.value = emptyList()
        _errorMessage.value = null
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
                val currentMovie = _searchResults.value.find { it.id == currentMovieId }
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
