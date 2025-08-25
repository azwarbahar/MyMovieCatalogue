package id.azwar.mymoviecatalogue.util

import androidx.navigation.NavType
import androidx.navigation.navArgument
import id.azwar.mymoviecatalogue.domain.model.Genre

sealed class MainDestinations(val route: String) {
    data object Home : MainDestinations("home")
    data object Search : MainDestinations("search")
    data object Details : MainDestinations("details/{movieId}") {
        fun createRoute(movieId: Long) = "details/$movieId"
    }
    data object Favorites : MainDestinations("favorites")
    data object Settings : MainDestinations("settings")
    data object Category : MainDestinations("category/{genreId}/{genreName}") {
        fun createRoute(genre: Genre) = "category/${genre.id}/${genre.name}"
    }
}

val mainNavigationGraph = listOf(
    MainDestinations.Home,
    MainDestinations.Search,
    MainDestinations.Details,
    MainDestinations.Favorites,
    MainDestinations.Settings,
    MainDestinations.Category
)
