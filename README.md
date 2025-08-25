# 🎬 My Movie Catalogue

A modern, feature-rich Android application that provides users with a comprehensive movie discovery experience using The Movie Database (TMDB) API. Built with cutting-edge Android development technologies and Material Design 3 principles.

## ✨ Features

- **🎯 Movie Discovery**: Browse trending movies, upcoming releases, and search across TMDB's extensive database
- **🏷️ Genre Categories**: Explore movies by genre with an attractive horizontal category list
- **🔍 Advanced Search**: Real-time search functionality with instant results
- **❤️ Favorites System**: Save and manage your favorite movies locally
- **📱 Modern UI/UX**: Beautiful Material Design 3 interface with smooth animations
- **🌙 Dark/Light Theme**: Toggle between light and dark themes
- **📊 Rich Movie Details**: Comprehensive movie information including ratings, reviews, and production details
- **🚀 Splash Screen**: Engaging animated splash screen with app branding

## 🛠️ Technology Stack

### **Core Technologies**
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Dependency Injection**: Hilt (Dagger)
- **Navigation**: Jetpack Navigation Compose

### **Libraries & Dependencies**

| Category | Library | Version | Purpose |
|----------|---------|---------|---------|
| **UI & Compose** | Jetpack Compose | Latest | Modern declarative UI toolkit |
| **Navigation** | Navigation Compose | Latest | Type-safe navigation |
| **State Management** | Compose Runtime | Latest | Reactive state management |
| **Networking** | Retrofit + OkHttp | Latest | HTTP client for API calls |
| **Image Loading** | Coil | Latest | Efficient image loading |
| **Database** | Room Persistence | Latest | Local data storage |
| **Coroutines** | Kotlin Coroutines | Latest | Asynchronous programming |
| **Dependency Injection** | Hilt | Latest | Dependency injection |
| **Material Design** | Material 3 | Latest | Modern design system |

## 📱 Screenshots

| Screen | Description | Screenshot |
|--------|-------------|------------|
| **Splash Screen** | Animated welcome screen with app branding | ![Splash](screenshots/splash.png) |
| **Home Screen** | Trending movies, genre categories, and upcoming releases | ![Home](screenshots/home.png) |
| **Search Screen** | Real-time movie search functionality | ![Search](screenshots/search.png) |
| **Movie Details** | Comprehensive movie information and ratings | ![Details](screenshots/details.png) |
| **Favorites** | Personal collection of saved movies | ![Favorites](screenshots/favorites.png) |
| **Category Screen** | Movies filtered by specific genres | ![Category](screenshots/category.png) |
| **Settings** | App preferences and user profile | ![Settings](screenshots/settings.png) |

## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 24 (API level 24) or higher
- Kotlin 1.8.0 or higher
- JDK 11 or higher

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/azwarbahar/MyMovieCatalogue.git
   cd MyMovieCatalogue
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned project folder
   - Click "OK"

3. **Sync Project**
   - Wait for Gradle sync to complete
   - Resolve any dependency issues if prompted

### 🔑 API Key Setup

This application requires a TMDB API key to function properly.

1. **Get TMDB API Key**
   - Visit [The Movie Database (TMDB)](https://www.themoviedb.org/)
   - Create an account or sign in
   - Go to [Settings > API](https://www.themoviedb.org/settings/api)
   - Request an API key (choose "Developer" option)
   - Copy your API key

2. **Configure API Key (Recommended Method)**
   
   **Option A: Using local.properties (Recommended)**
   - Create or open the `local.properties` file in your project root directory
   - Add your TMDB API key:
     ```
     tmdb.api.key=your_actual_api_key_here
     ```
   - Example:
     ```
     tmdb.api.key=1234567890abcdef1234567890abcdef
     ```

   **Option B: Direct Code Replacement**
   - Open `app/src/main/java/id/azwar/mymoviecatalogue/data/remote/NetworkModule.kt`
   - Find the line containing the API key
   - Replace `BuildConfig.TMDB_API_KEY` with your actual TMDB API key
   
## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2024 Muhammad Azwar Bahar

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

⭐ **If you find this project helpful, please give it a star!** ⭐

Made with ❤️ by [Muhammad Azwar Bahar](https://github.com/azwarbahar)
