package id.azwar.mymoviecatalogue.ui.components.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import id.azwar.mymoviecatalogue.ui.theme.MovieCatalogueTheme
import id.azwar.mymoviecatalogue.util.MainDestinations
import id.azwar.mymoviecatalogue.ui.features.home.HomeScreen
import id.azwar.mymoviecatalogue.ui.features.search.SearchScreen
import id.azwar.mymoviecatalogue.ui.features.favorites.FavoritesScreen
import id.azwar.mymoviecatalogue.ui.features.settings.SettingsScreen
import id.azwar.mymoviecatalogue.ui.features.details.DetailsScreen
import id.azwar.mymoviecatalogue.ui.features.category.CategoryScreen
import id.azwar.mymoviecatalogue.ui.features.splash.SplashScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument
import id.azwar.mymoviecatalogue.domain.model.Genre

@Composable
fun RootNavigationScaffold() {
    val navController: NavHostController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    var showSplash by rememberSaveable { mutableStateOf(true) }

    val navigationItems = listOf(
        NavigationItem(
            route = MainDestinations.Home.route,
            icon = Icons.Default.Home,
            label = stringResource(id.azwar.mymoviecatalogue.R.string.home_title)
        ),
        NavigationItem(
            route = MainDestinations.Search.route,
            icon = Icons.Default.Search,
            label = stringResource(id.azwar.mymoviecatalogue.R.string.search_title)
        ),
        NavigationItem(
            route = MainDestinations.Favorites.route,
            icon = Icons.Default.Favorite,
            label = stringResource(id.azwar.mymoviecatalogue.R.string.favorites_title)
        ),
        NavigationItem(
            route = MainDestinations.Settings.route,
            icon = Icons.Default.Settings,
            label = stringResource(id.azwar.mymoviecatalogue.R.string.settings_title)
        )
    )

    if (showSplash) {
        SplashScreen(
            onSplashComplete = {
                showSplash = false
            }
        )
    } else {
        androidx.compose.material3.Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                NavigationBar {
                    navigationItems.forEach { item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label
                                )
                            },
                            label = { Text(text = item.label) },
                            selected = currentRoute == item.route,
                            onClick = {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = MainDestinations.Home.route,
                modifier = Modifier
            ) {
                composable(MainDestinations.Home.route) {
                    HomeScreen(
                        onMovieClick = { movie ->
                            navController.navigate(MainDestinations.Details.createRoute(movie.id))
                        },
                        onSearchClick = {
                            navController.navigate(MainDestinations.Search.route)
                        },
                        onGenreClick = { genre ->
                            navController.navigate(MainDestinations.Category.createRoute(genre))
                        }
                    )
                }

                composable(MainDestinations.Search.route) {
                    SearchScreen(
                        onMovieClick = { movie ->
                            navController.navigate(MainDestinations.Details.createRoute(movie.id))
                        }
                    )
                }

                composable(
                    route = MainDestinations.Details.route,
                    arguments = listOf(
                        navArgument("movieId") { type = NavType.LongType }
                    )
                ) { backStackEntry ->
                    val movieId = backStackEntry.arguments?.getLong("movieId") ?: 0L
                    DetailsScreen(
                        movieId = movieId,
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }

                composable(MainDestinations.Favorites.route) {
                    FavoritesScreen(
                        onMovieClick = { movieId ->
                            navController.navigate(MainDestinations.Details.createRoute(movieId))
                        }
                    )
                }

                composable(MainDestinations.Settings.route) {
                    SettingsScreen()
                }

                composable(
                    route = MainDestinations.Category.route,
                    arguments = listOf(
                        navArgument("genreId") { type = NavType.IntType },
                        navArgument("genreName") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val genreId = backStackEntry.arguments?.getInt("genreId") ?: 0
                    val genreName = backStackEntry.arguments?.getString("genreName") ?: ""


                    val genre = Genre(genreId, genreName)

                    CategoryScreen(
                        genre = genre,
                        onMovieClick = { movie ->
                            navController.navigate(MainDestinations.Details.createRoute(movie.id))
                        },
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}

private data class NavigationItem(
    val route: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val label: String
)

@Preview
@Composable
fun RootNavigationScaffoldPreview() {
    MovieCatalogueTheme {
        RootNavigationScaffold()
    }
}
