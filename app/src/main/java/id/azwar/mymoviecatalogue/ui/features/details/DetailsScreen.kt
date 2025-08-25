package id.azwar.mymoviecatalogue.ui.features.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import id.azwar.mymoviecatalogue.R
import id.azwar.mymoviecatalogue.domain.model.MovieDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    movieDetails: MovieDetails?, 
    setMovieId: (Long) -> Unit, 
    toggleFavorite: () -> Unit, 
    isFavorite: Boolean,
    onBackPressed: () -> Unit,
    fromFavorites: Boolean = false
) {
    val posterUrl = "https://image.tmdb.org/t/p/w500"
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text("") },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )
        
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(stringResource(R.string.tmdb_movie_header), style = MaterialTheme.typography.headlineMedium)
            
            Card(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(450.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    AsyncImage(
                        model = posterUrl + movieDetails?.posterPath,
                        contentDescription = stringResource(R.string.movie_poster),
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxWidth()
                    )

                    IconButton(
                        onClick = {
                            if (movieDetails != null) {
                                setMovieId(movieDetails.id)
                            }
                            toggleFavorite()
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
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
            
            Spacer(modifier = Modifier.height(5.dp))
            
            if (movieDetails != null) {
                Text(text = movieDetails.title, style = MaterialTheme.typography.headlineMedium)
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                Text(
                    text = stringResource(R.string.overview)
                )
                Text(movieDetails.overview)
                Spacer(modifier = Modifier.height(5.dp))
                Text(if (movieDetails.adult) stringResource(R.string.adult) else stringResource(R.string.not_adult))
                Text(movieDetails.releaseDate)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text("(" + movieDetails.productionCompanies[0].name + ")")
                    movieDetails.genres.forEach { it ->
                        Text(it.name)
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                val hours = movieDetails.runtime / 60
                val minutes = movieDetails.runtime % 60
                val hoursPart = if (hours > 0) "${hours}hr" else ""
                val minutesPart = if (minutes > 0) "${minutes}m" else ""
                val formattedRuntime = when {
                    hours > 0 && minutes > 0 -> "$hoursPart $minutesPart"
                    hours > 0 && minutes == 0L -> hoursPart
                    hours == 0L && minutes > 0 -> minutesPart
                    else -> "0m"
                }
                Text(formattedRuntime)
                Spacer(modifier = Modifier.height(5.dp))
                Text(movieDetails.title)
                Text(stringResource(R.string.director_author))
            }
        }
    }
}
