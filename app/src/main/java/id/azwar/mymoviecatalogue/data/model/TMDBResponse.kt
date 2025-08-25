package id.azwar.mymoviecatalogue.data.model

import com.google.gson.annotations.SerializedName

data class TMDBResponse(
    val page: Int,
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class MovieDto(
    val id: Long,
    val title: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Long,
    val popularity: Double,
    val adult: Boolean,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("media_type")
    val mediaType: String,
    val video: Boolean,
    @SerializedName("genre_ids")
    val genreIds: List<Int>
)

data class MovieDetailsDto(
    val id: Long,
    val title: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Long,
    val popularity: Double,
    val adult: Boolean,
    @SerializedName("original_language")
    val originalLanguage: String,
    val runtime: Long,
    val genres: List<GenreDto>,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyDto>
)

data class GenreDto(
    val id: Int,
    val name: String
)

data class ProductionCompanyDto(
    val id: Int,
    val name: String,
    @SerializedName("logo_path")
    val logoPath: String?,
    @SerializedName("origin_country")
    val originCountry: String
)
