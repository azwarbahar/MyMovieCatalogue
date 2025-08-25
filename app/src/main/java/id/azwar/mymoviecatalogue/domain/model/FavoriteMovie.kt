package id.azwar.mymoviecatalogue.domain.model

data class FavoriteMovie(
    val id: Long,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Long,
    val popularity: Double,
    val adult: Boolean,
    val originalLanguage: String,
    val mediaType: String,
    val video: Boolean,
    val addedAt: Long = System.currentTimeMillis()
)
