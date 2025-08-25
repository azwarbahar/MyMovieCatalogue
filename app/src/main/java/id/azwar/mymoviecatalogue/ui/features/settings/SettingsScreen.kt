package id.azwar.mymoviecatalogue.ui.features.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.azwar.mymoviecatalogue.R
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.launch
import id.azwar.mymoviecatalogue.ui.theme.rememberThemeManager
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.statusBars
import id.azwar.mymoviecatalogue.ui.components.*

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val themeManager = rememberThemeManager()

    val isDarkTheme by themeManager.isDarkTheme.collectAsState(initial = false)

    val onThemeChange = { newTheme: Boolean ->
        scope.launch {
            themeManager.setDarkTheme(newTheme)
        }
        Unit
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surface,
                            MaterialTheme.colorScheme.surfaceVariant
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
                .padding(bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = stringResource(R.string.settings_title),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            ProfileSection(
                name = "Muhammad Azwar Bahar",
                email = "Android Developer"
            )

            HorizontalDivider()

            ThemeToggleSection(
                isDarkMode = isDarkTheme,
                onThemeToggle = { onThemeChange(!isDarkTheme) }
            )

            HorizontalDivider()

            SocialMediaSection(context)

            HorizontalDivider()

            AboutSection(
                appName = "My Movie Catalogue",
                version = "1.0.0",
                description = "Movie Catalogue is an application created for technical testing that displays a catalog of movies from various genres. This application uses data from The Movie Database (TMDB) API to display current movie information, including posters, ratings, synopses, and complete movie details. The main features of the application include trending movies list, favorites system, and responsive navigation using Material 3 Adaptive Navigation.",
                githubUrl = "https://github.com/azwarbahar/MyMovieCatalogue",
                onGithubClick = {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/azwarbahar/MyMovieCatalogue"))
                        context.startActivity(intent)
                    } catch (e: Exception) {

                    }
                }
            )
        }
    }
}

@Composable
private fun SocialMediaSection(context: android.content.Context) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Social Media & Contact",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            SocialMediaItem(
                icon = Icons.Default.Star,
                text = "GitHub",
                url = "https://github.com/azwarbahar/MyMovieCatalogue",
                onClick = {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/azwarbahar/MyMovieCatalogue"))
                        context.startActivity(intent)
                    } catch (e: Exception) {

                    }
                }
            )

            SocialMediaItem(
                icon = Icons.Default.Person,
                text = "LinkedIn",
                url = "https://www.linkedin.com/in/-azwar-bahar/",
                onClick = {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/-azwar-bahar/"))
                        context.startActivity(intent)
                    } catch (e: Exception) {

                    }
                }
            )

            SocialMediaItem(
                icon = Icons.Default.Person,
                text = "Instagram",
                url = "https://www.instagram.com/azwarbahar_/",
                onClick = {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/azwarbahar_/"))
                        context.startActivity(intent)
                    } catch (e: Exception) {

                    }
                }
            )

            SocialMediaItem(
                icon = Icons.Default.Email,
                text = "Email",
                url = "azwarbahar07@gmail.com",
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:azwarbahar07@gmail.com")
                        putExtra(Intent.EXTRA_SUBJECT, "Contact from Movie Catalogue App")
                    }
                    context.startActivity(intent)
                }
            )
        }
    }
}
