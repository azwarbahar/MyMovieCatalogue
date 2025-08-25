import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.serialization)
    id("kotlin-kapt")
}

android {
    namespace = "id.azwar.mymoviecatalogue"
    compileSdk = 35

    defaultConfig {
        applicationId = "id.azwar.mymoviecatalogue"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // --- Read properties from local.properties ---
        val properties = Properties()
        if (rootProject.file("local.properties").exists()) {
            rootProject.file("local.properties").inputStream().use { inputStream ->
                properties.load(inputStream)
            }
        }

        // --- Expose them to BuildConfig ---
        buildConfigField("String", "TMDB_API_KEY", "\"${properties.getProperty("tmdb.api.key")}\"")
        buildConfigField("String", "TMDB_AUTH_TOKEN", "\"${properties.getProperty("tmdb.auth.token")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose.android)
    implementation(libs.androidx.material3.adaptive.navigation.suite.android)
    implementation(libs.androidx.adaptive.navigation.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.okhttp) // HTTP client
    implementation(libs.logging.interceptor) // For logging network requests
    implementation(libs.retrofit) // turns http api into kotlin interface
    implementation(libs.converter.gson) // JSON to Kotlin Object
    implementation(libs.retrofit2.kotlin.coroutines.adapter) // Coroutine adapter for Retrofit (to use suspend functions)
    implementation(libs.coil.compose) // Coil for AsyncImage
    
    // DataStore for theme preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    
    // Room database
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
}