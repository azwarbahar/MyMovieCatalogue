package id.azwar.mymoviecatalogue.util

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import id.azwar.mymoviecatalogue.R
import kotlinx.serialization.Serializable

@Serializable
sealed class MainNavigationBuilder {
    @Serializable data object Home : MainNavigationBuilder()
    @Serializable data object Search : MainNavigationBuilder()
    @Serializable data object Favorites : MainNavigationBuilder()
    @Serializable data object Settings : MainNavigationBuilder()
    @Serializable data class Details(val movieId: Long, val fromFavorites: Boolean = false) : MainNavigationBuilder()

    enum class MainDestinations(
        @StringRes val label: Int,
        val iconFilled: ImageVector,
        val iconOutlined: ImageVector,
        @StringRes val contentDescription: Int,
        val view: MainNavigationBuilder,
    ) {
        HOME(
            R.string.home_title,
            Icons.Filled.Home,
            Icons.Outlined.Home,
            R.string.home_title,
            Home,
        ),
        SEARCH(
            R.string.search_title,
            Icons.Filled.Search,
            Icons.Outlined.Search,
            R.string.search_title,
            Search,
        ),
        FAVORITES(
            R.string.favorites_title,
            Icons.Filled.Favorite,
            Icons.Outlined.FavoriteBorder,
            R.string.favorites_title,
            Favorites,
        ),
        SETTINGS(
            R.string.settings_title,
            Icons.Filled.Settings,
            Icons.Outlined.Settings,
            R.string.settings_title,
            Settings,
        ),
    }
}