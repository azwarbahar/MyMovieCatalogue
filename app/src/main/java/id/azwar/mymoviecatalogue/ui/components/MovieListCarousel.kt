package id.azwar.mymoviecatalogue.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import id.azwar.mymoviecatalogue.domain.model.Movie
import coil.compose.AsyncImage
import id.azwar.mymoviecatalogue.R
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListCarousel(
    onCardClick: (Long) -> Unit,
    movies: List<Movie>,
    favoriteMovieIds: List<Long>,
    toggleFavorite: () -> Unit,
    setMovieId: (Long) -> Unit,
) {
    val posterURL = "https://image.tmdb.org/t/p/w500"
    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { movies.count() },
        modifier = Modifier
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
                    onCardClick(movie.id)
                }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AsyncImage(
                        model = posterURL + movie.posterPath,
                        contentDescription = stringResource(R.string.movie_poster),
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier.fillMaxSize()
                    )

                    val isFavorite = favoriteMovieIds.contains(movie.id)
                    IconButton(
                        onClick = {
                            setMovieId(movie.id)
                            toggleFavorite()
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = if (isFavorite) stringResource(R.string.favorite) else stringResource(
                                R.string.remove_favorite
                            ),
                            tint = Color.Red
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
                    contentDescription = stringResource(R.string.rating),
                    tint = Color(0xFFEBB400)
                )
                Spacer(modifier = Modifier.width(5.dp))
                val formattedRating = String.format(Locale.US, "%.1f", movie.voteAverage)
                Text(formattedRating)
            }
        }
    }
}

