package id.azwar.mymoviecatalogue.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.azwar.mymoviecatalogue.domain.model.Genre
import id.azwar.mymoviecatalogue.R

@Composable
fun GenreCategoryList(
    genres: List<Genre>,
    onGenreClick: (Genre) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        items(genres) { genre ->
            GenreCategoryItem(
                genre = genre,
                onClick = { onGenreClick(genre) }
            )
        }
    }
}

@Composable
private fun GenreCategoryItem(
    genre: Genre,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val genreIcon = getGenreIcon(genre.name)
    val genreColor = getGenreColor(genre.name)

    Card(
        modifier = modifier
            .width(120.dp)
            .height(100.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = genreColor.copy(alpha = 0.1f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = genreIcon,
                contentDescription = stringResource(R.string.genre_icon, genre.name),
                tint = genreColor,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = genre.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2
            )
        }
    }
}

@Composable
private fun getGenreIcon(genreName: String): ImageVector {
    return when (genreName.lowercase()) {
        "action" -> Icons.Default.Star
        "adventure" -> Icons.Default.Star
        "animation" -> Icons.Default.PlayArrow
        "comedy" -> Icons.Default.Star
        "crime" -> Icons.Default.Star
        "documentary" -> Icons.Default.Star
        "drama" -> Icons.Default.Star
        "family" -> Icons.Default.Star
        "fantasy" -> Icons.Default.Star
        "history" -> Icons.Default.Star
        "horror" -> Icons.Default.Star
        "music" -> Icons.Default.Star
        "mystery" -> Icons.Default.Star
        "romance" -> Icons.Default.Favorite
        "science fiction" -> Icons.Default.Star
        "tv movie" -> Icons.Default.Star
        "thriller" -> Icons.Default.Star
        "war" -> Icons.Default.Star
        "western" -> Icons.Default.Star
        else -> Icons.Default.Star
    }
}

@Composable
private fun getGenreColor(genreName: String): Color {
    return when (genreName.lowercase()) {
        "action" -> Color(0xFFE74C3C)
        "adventure" -> Color(0xFF3498DB)
        "animation" -> Color(0xFF9B59B6)
        "comedy" -> Color(0xFFF1C40F)
        "crime" -> Color(0xFF34495E)
        "documentary" -> Color(0xFF27AE60)
        "drama" -> Color(0xFFE67E22)
        "family" -> Color(0xFF1ABC9C)
        "fantasy" -> Color(0xFF8E44AD)
        "history" -> Color(0xFF95A5A6)
        "horror" -> Color(0xFF2C3E50)
        "music" -> Color(0xFFE91E63)
        "mystery" -> Color(0xFF607D8B)
        "romance" -> Color(0xFFE91E63)
        "science fiction" -> Color(0xFF00BCD4)
        "tv movie" -> Color(0xFF795548)
        "thriller" -> Color(0xFF3F51B5)
        "war" -> Color(0xFFD32F2F)
        "western" -> Color(0xFF8D6E63)
        else -> Color(0xFF607D8B)
    }
}
