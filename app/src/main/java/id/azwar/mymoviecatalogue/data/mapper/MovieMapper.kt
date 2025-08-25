package id.azwar.mymoviecatalogue.data.mapper

import id.azwar.mymoviecatalogue.data.model.MovieDto
import id.azwar.mymoviecatalogue.data.model.MovieDetailsDto
import id.azwar.mymoviecatalogue.data.model.GenreDto
import id.azwar.mymoviecatalogue.data.model.ProductionCompanyDto
import id.azwar.mymoviecatalogue.domain.model.Movie
import id.azwar.mymoviecatalogue.domain.model.MovieDetails
import id.azwar.mymoviecatalogue.domain.model.Genre
import id.azwar.mymoviecatalogue.domain.model.ProductionCompany

class MovieMapper {

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
            productionCompanies = movieDetailsDto.productionCompanies.map { 
                ProductionCompany(
                    id = it.id,
                    name = it.name,
                    logoPath = it.logoPath,
                    originCountry = it.originCountry
                )
            }
        )
    }

    fun mapGenreDtoToDomain(genreDto: GenreDto): Genre {
        return Genre(
            id = genreDto.id,
            name = genreDto.name
        )
    }

    fun mapDomainToFavoriteMovieEntity(movie: Movie): id.azwar.mymoviecatalogue.data.local.entity.FavoriteMovieEntity {
        return id.azwar.mymoviecatalogue.data.local.entity.FavoriteMovieEntity(
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

    fun mapFavoriteMovieEntityToDomain(entity: id.azwar.mymoviecatalogue.data.local.entity.FavoriteMovieEntity): id.azwar.mymoviecatalogue.domain.model.FavoriteMovie {
        return id.azwar.mymoviecatalogue.domain.model.FavoriteMovie(
            id = entity.id,
            title = entity.title,
            originalTitle = entity.originalTitle,
            overview = entity.overview,
            posterPath = entity.posterPath,
            backdropPath = entity.backdropPath,
            releaseDate = entity.releaseDate,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount,
            popularity = entity.popularity,
            adult = entity.adult,
            originalLanguage = entity.originalLanguage,
            mediaType = entity.mediaType,
            video = entity.video,
            addedAt = entity.addedAt
        )
    }
}
