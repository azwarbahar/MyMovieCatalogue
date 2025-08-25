package id.azwar.mymoviecatalogue.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.azwar.mymoviecatalogue.domain.model.Movie
import coil.compose.AsyncImage
import id.azwar.mymoviecatalogue.R
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListCarousel(
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    onFavoriteClick: (Movie) -> Unit,
    favoriteMovieIds: List<Long>,
    modifier: Modifier = Modifier
) {
    val posterURL = "https://image.tmdb.org/t/p/w500"
    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { movies.count() },
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp),
        preferredItemWidth = 200.dp,
        itemSpacing = 8.dp,
    ) { i ->
        Column(
            modifier = Modifier.width(200.dp)
        ) {
            val movie = movies[i]
            Card(
                modifier = Modifier
                    .width(200.dp)
                    .height(300.dp)
                    .maskClip(MaterialTheme.shapes.extraLarge),
                onClick = {
                    onMovieClick(movie)
                }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (movie.posterPath != null) {
                        AsyncImage(
                            model = posterURL + movie.posterPath,
                            contentDescription = stringResource(R.string.movie_poster, movie.title),
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.surfaceVariant),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = movie.title,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                    val isFavorite = favoriteMovieIds.contains(movie.id)
                    IconButton(
                        onClick = {
                            onFavoriteClick(movie)
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .size(32.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                                shape = MaterialTheme.shapes.medium
                            )
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = stringResource(
                                if (isFavorite) R.string.remove_from_favorites else R.string.add_to_favorites
                            ),
                            tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(movie.title)
            Row(
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = stringResource(R.string.rating_label),
                    tint = Color(0xFFEBB400)
                )
                Spacer(modifier = Modifier.width(5.dp))
                val formattedRating = String.format(Locale.US, "%.1f", movie.voteAverage)
                Text(formattedRating)
            }
        }
    }
}

