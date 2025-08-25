package id.azwar.mymoviecatalogue.data.mapper

import id.azwar.mymoviecatalogue.data.model.MovieDto
import id.azwar.mymoviecatalogue.data.model.MovieDetailsDto
import id.azwar.mymoviecatalogue.data.model.GenreDto
import id.azwar.mymoviecatalogue.data.model.ProductionCompanyDto
import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.model.MovieDetails
import id.azwar.mymoviecatalogue.domain.model.Genre
import id.azwar.mymoviecatalogue.domain.model.ProductionCompany
import id.azwar.mymoviecatalogue.data.local.entity.FavoriteMovieEntity
import id.azwar.mymoviecatalogue.domain.model.FavoriteMovie

object MovieMapper {
    
    fun mapMovieDtoToDomain(movieDto: MovieDto): Movie {
        return Movie(
            id = movieDto.id,
            title = movieDto.title,
            originalTitle = movieDto.originalTitle,
            overview = movieDto.overview,
            posterPath = movieDto.posterPath,
            backdropPath = movieDto.backdropPath,
            releaseDate = movieDto.releaseDate,
            voteAverage = movieDto.voteAverage,
            voteCount = movieDto.voteCount,
            popularity = movieDto.popularity,
            adult = movieDto.adult,
            originalLanguage = movieDto.originalLanguage,
            mediaType = movieDto.mediaType,
            video = movieDto.video,
            genreIds = movieDto.genreIds
        )
    }
    
    fun mapMovieDetailsDtoToDomain(movieDetailsDto: MovieDetailsDto): MovieDetails {
        return MovieDetails(
            id = movieDetailsDto.id,
            title = movieDetailsDto.title,
            originalTitle = movieDetailsDto.originalTitle,
            overview = movieDetailsDto.overview,
            posterPath = movieDetailsDto.posterPath,
            backdropPath = movieDetailsDto.backdropPath,
            releaseDate = movieDetailsDto.releaseDate,
            voteAverage = movieDetailsDto.voteAverage,
            voteCount = movieDetailsDto.voteCount,
            popularity = movieDetailsDto.popularity,
            adult = movieDetailsDto.adult,
            originalLanguage = movieDetailsDto.originalLanguage,
            runtime = movieDetailsDto.runtime,
            genres = movieDetailsDto.genres.map { mapGenreDtoToDomain(it) },
            productionCompanies = movieDetailsDto.productionCompanies.map { mapProductionCompanyDtoToDomain(it) }
        )
    }
    
    private fun mapGenreDtoToDomain(genreDto: GenreDto): Genre {
        return Genre(
            id = genreDto.id,
            name = genreDto.name
        )
    }
    
    private fun mapProductionCompanyDtoToDomain(productionCompanyDto: ProductionCompanyDto): ProductionCompany {
        return ProductionCompany(
            id = productionCompanyDto.id,
            name = productionCompanyDto.name,
            logoPath = productionCompanyDto.logoPath,
            originCountry = productionCompanyDto.originCountry
        )
    }
    
    fun mapMovieToFavoriteMovie(movie: Movie): FavoriteMovie {
        return FavoriteMovie(
            id = movie.id,
            title = movie.title,
            originalTitle = movie.originalTitle,
            overview = movie.overview,
            posterPath = movie.posterPath,
            backdropPath = movie.backdropPath,
            releaseDate = movie.releaseDate,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
            popularity = movie.popularity,
            adult = movie.adult,
            originalLanguage = movie.originalLanguage,
            mediaType = movie.mediaType,
            video = movie.video
        )
    }
    
    fun mapFavoriteMovieEntityToDomain(favoriteMovieEntity: FavoriteMovieEntity): FavoriteMovie {
        return FavoriteMovie(
            id = favoriteMovieEntity.id,
            title = favoriteMovieEntity.title,
            originalTitle = favoriteMovieEntity.originalTitle,
            overview = favoriteMovieEntity.overview,
            posterPath = favoriteMovieEntity.posterPath,
            backdropPath = favoriteMovieEntity.backdropPath,
            releaseDate = favoriteMovieEntity.releaseDate,
            voteAverage = favoriteMovieEntity.voteAverage,
            voteCount = favoriteMovieEntity.voteCount,
            popularity = favoriteMovieEntity.popularity,
            adult = favoriteMovieEntity.adult,
            originalLanguage = favoriteMovieEntity.originalLanguage,
            mediaType = favoriteMovieEntity.mediaType,
            video = favoriteMovieEntity.video,
            addedAt = favoriteMovieEntity.addedAt
        )
    }
    
    fun mapFavoriteMovieToEntity(favoriteMovie: FavoriteMovie): FavoriteMovieEntity {
        return FavoriteMovieEntity(
            id = favoriteMovie.id,
            title = favoriteMovie.title,
            originalTitle = favoriteMovie.originalTitle,
            overview = favoriteMovie.overview,
            posterPath = favoriteMovie.posterPath,
            backdropPath = favoriteMovie.backdropPath,
            releaseDate = favoriteMovie.releaseDate,
            voteAverage = favoriteMovie.voteAverage,
            voteCount = favoriteMovie.voteCount,
            popularity = favoriteMovie.popularity,
            adult = favoriteMovie.adult,
            originalLanguage = favoriteMovie.originalLanguage,
            mediaType = favoriteMovie.mediaType,
            video = favoriteMovie.video,
            addedAt = favoriteMovie.addedAt
        )
    }
}
