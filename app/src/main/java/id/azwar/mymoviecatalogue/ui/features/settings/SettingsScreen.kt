package id.azwar.mymoviecatalogue.ui.features.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.azwar.mymoviecatalogue.R
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.launch
import id.azwar.mymoviecatalogue.ui.theme.rememberThemeManager

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
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = stringResource(R.string.settings_title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        ProfileSection()
        
        HorizontalDivider()
        
        ThemeToggleSection(
            isDarkTheme = isDarkTheme, 
            onThemeChange = onThemeChange
        )
        
        HorizontalDivider()
        
        AboutSection()
    }
}

@Composable
private fun ProfileSection() {
    val context = LocalContext.current
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = androidx.compose.material3.CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.Gray.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Photo",
                    modifier = Modifier.size(60.dp),
                    tint = Color.Gray
                )
            }
            
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Muhammad Azwar Bahar",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Android Developer",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            SocialMediaLinks(context)
        }
    }
}

@Composable
private fun SocialMediaLinks(context: android.content.Context) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SocialMediaItem(
            icon = Icons.Default.Person,
            text = "LinkedIn",
            url = "https://www.linkedin.com/in/-azwar-bahar/",
            onClick = {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/-azwar-bahar/"))
                    context.startActivity(intent)
                } catch (e: Exception) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/-azwar-bahar/"))
                    context.startActivity(intent)
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
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/azwarbahar_/"))
                    context.startActivity(intent)
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

@Composable
private fun SocialMediaItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    url: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        
        Column {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = url,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun ThemeToggleSection(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = androidx.compose.material3.CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Theme Settings",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = if (isDarkTheme) Icons.Default.Star else Icons.Default.Person,
                        contentDescription = if (isDarkTheme) "Dark Mode" else "Light Mode",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = if (isDarkTheme) "Dark Theme" else "Light Theme",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                
                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = onThemeChange
                )
            }
            
            Text(
                text = if (isDarkTheme) 
                    "Aplikasi akan menggunakan tema gelap dengan warna yang lebih kontras" 
                else 
                    "Aplikasi akan menggunakan tema terang dengan warna yang lebih soft",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun AboutSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = androidx.compose.material3.CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "About",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Movie Catalogue adalah aplikasi yang dibuat untuk technical test yang menampilkan katalog film dari berbagai genre. Aplikasi ini menggunakan data dari The Movie Database (TMDB) API untuk menampilkan informasi film terkini, termasuk poster, rating, sinopsis, dan detail lengkap film. Fitur utama aplikasi meliputi daftar trending movies, sistem favorit, dan navigasi yang responsif menggunakan Material 3 Adaptive Navigation.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )
            
            Text(
                text = "Data Source: The Movie Database (TMDB)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
            )
        }
    }
}
