package id.azwar.mymoviecatalogue.domain.model

data class MovieDetails(
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
    val runtime: Long,
    val genres: List<Genre>,
    val productionCompanies: List<ProductionCompany>
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val name: String,
    val logoPath: String?,
    val originCountry: String
)
