package id.azwar.mymoviecatalogue.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import id.azwar.mymoviecatalogue.R
import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.model.Genre
import id.azwar.mymoviecatalogue.ui.components.MovieListCarousel
import id.azwar.mymoviecatalogue.ui.components.GenreCategoryList
import id.azwar.mymoviecatalogue.ui.components.UpcomingMoviesList
import id.azwar.mymoviecatalogue.viewModels.HomeViewModel

@Composable
fun HomeScreen(
    onMovieClick: (Movie) -> Unit,
    onSearchClick: () -> Unit,
    onGenreClick: (Genre) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val trendingMovies by viewModel.trendingMovies.collectAsStateWithLifecycle()
    val genres by viewModel.genres.collectAsStateWithLifecycle()
    val upcomingMovies by viewModel.upcomingMovies.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val favoriteMovieIds by viewModel.favoriteMovieIds.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surface,
                            MaterialTheme.colorScheme.surfaceVariant
                        )
                    )
                )
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 80.dp) // Add bottom padding for bottom navigation
        ) {

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .windowInsetsPadding(WindowInsets.statusBars)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.home_title),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold
                    )

                    IconButton(
                        onClick = onSearchClick,
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = MaterialTheme.shapes.medium
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.search_title),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }


            if (trendingMovies.isNotEmpty()) {
                item {
                    Text(
                        text = stringResource(R.string.trending_movies),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                item {
                    MovieListCarousel(
                        movies = trendingMovies,
                        onMovieClick = onMovieClick,
                        onFavoriteClick = { movie ->
                            viewModel.setMovieId(movie.id)
                            viewModel.toggleFavorite()
                        },
                        favoriteMovieIds = favoriteMovieIds,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }


            if (genres.isNotEmpty()) {
                item {
                    Text(
                        text = stringResource(R.string.categories),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                    )
                }

                item {
                    GenreCategoryList(
                        genres = genres,
                        onGenreClick = onGenreClick,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }


            if (upcomingMovies.isNotEmpty()) {
                item {
                    Text(
                        text = stringResource(R.string.upcoming_movies),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                    )
                }

                item {
                    UpcomingMoviesList(
                        movies = upcomingMovies,
                        onMovieClick = onMovieClick,
                        onFavoriteClick = { movie ->
                            viewModel.setMovieId(movie.id)
                            viewModel.toggleFavorite()
                        },
                        favoriteMovieIds = favoriteMovieIds,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }


            if (isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }


            errorMessage?.let { error ->
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onErrorContainer
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = error,
                                color = MaterialTheme.colorScheme.onErrorContainer,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }


            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}
