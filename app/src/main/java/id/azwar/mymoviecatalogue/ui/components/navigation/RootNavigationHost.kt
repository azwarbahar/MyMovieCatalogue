package id.azwar.mymoviecatalogue.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.azwar.mymoviecatalogue.viewModels.FavoritesViewModel
import id.azwar.mymoviecatalogue.viewModels.HomeViewModel
import id.azwar.mymoviecatalogue.viewModels.DetailsViewModel
import id.azwar.mymoviecatalogue.ui.features.favorites.FavoritesScreen
import id.azwar.mymoviecatalogue.ui.features.home.HomeScreen
import id.azwar.mymoviecatalogue.ui.features.settings.SettingsScreen
import id.azwar.mymoviecatalogue.ui.features.details.DetailsScreen
import id.azwar.mymoviecatalogue.util.MainNavigationBuilder


@Composable
fun RootNavigationHost(
    navController: NavHostController
) {
    NavHost(
        navController,
        startDestination = MainNavigationBuilder.Home
    ) {
        composable<MainNavigationBuilder.Home> {
            HomeScreen(
                hiltViewModel<HomeViewModel>(),
                onMovieClick = { movieId ->
                    navController.navigate(MainNavigationBuilder.Details(movieId, false))
                }
            )
        }

        composable<MainNavigationBuilder.Favorites> {
            FavoritesScreen(
                hiltViewModel<FavoritesViewModel>(),
                onMovieClick = { movieId ->
                    navController.navigate(MainNavigationBuilder.Details(movieId, true))
                }
            )
        }

        composable<MainNavigationBuilder.Settings> {
            SettingsScreen()
        }
        
        composable<MainNavigationBuilder.Details> { backStackEntry ->
            val details = backStackEntry.arguments?.let { args ->
                MainNavigationBuilder.Details(
                    movieId = args.getLong("movieId"),
                    fromFavorites = args.getBoolean("fromFavorites", false)
                )
            }
            
            details?.let { detailRoute ->
                val detailsViewModel = hiltViewModel<DetailsViewModel>()
                
                detailsViewModel.setMovieIdAndFetch(detailRoute.movieId)
                
                val movieDetails = detailsViewModel.movieDetails.collectAsState().value
                val isLoading = detailsViewModel.isLoading.collectAsState().value
                val errorMessage = detailsViewModel.errorMessage.collectAsState().value
                val favoriteMovieIds = detailsViewModel.favoriteMovieIds.collectAsState().value
                
                DetailsScreen(
                    movieDetails = movieDetails,
                    setMovieId = { movieId ->
                        detailsViewModel.setMovieIdAndFetch(movieId)
                    },
                    toggleFavorite = { detailsViewModel.toggleFavorite() },
                    isFavorite = favoriteMovieIds.contains(detailRoute.movieId),
                    onBackPressed = { navController.popBackStack() },
                    fromFavorites = detailRoute.fromFavorites
                )
            }
        }
    }
}