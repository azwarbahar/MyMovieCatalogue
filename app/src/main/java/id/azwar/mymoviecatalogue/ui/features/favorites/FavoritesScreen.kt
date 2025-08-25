package id.azwar.mymoviecatalogue.ui.features.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import id.azwar.mymoviecatalogue.R
import id.azwar.mymoviecatalogue.domain.model.FavoriteMovie
import id.azwar.mymoviecatalogue.viewModels.FavoritesViewModel

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onMovieClick: (Long) -> Unit = {}
) {
    val favoriteMovies by viewModel.favoriteMovies.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    BuildFavoritesScreen(
        favoriteMovies = favoriteMovies, 
        isLoading = isLoading, 
        errorMessage = errorMessage, 
        onRemoveFavorite = viewModel::removeFavorite,
        onMovieClick = onMovieClick
    )
}

@Composable
fun BuildFavoritesScreen(
    favoriteMovies: List<FavoriteMovie>,
    isLoading: Boolean,
    errorMessage: String?,
    onRemoveFavorite: (Long) -> Unit,
    onMovieClick: (Long) -> Unit
) {
    val posterURL = "https://image.tmdb.org/t/p/w185"

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 30.dp,20.dp,0.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(stringResource(R.string.tmdb_movie_header), style = MaterialTheme.typography.headlineMedium)
            Text(stringResource(R.string.favorites), style = MaterialTheme.typography.headlineSmall)

            favoriteMovies.forEachIndexed { _, movie ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onMovieClick(movie.id) },
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = posterURL + movie.posterPath,
                        contentDescription = stringResource(R.string.movie_poster),
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .width(50.dp)
                            .height(100.dp)
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = movie.title
                    )
                    IconButton(
                        onClick = {
                            onRemoveFavorite(movie.id)
                        },
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = stringResource(R.string.delete),
                        )
                    }
                }
                HorizontalDivider(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
