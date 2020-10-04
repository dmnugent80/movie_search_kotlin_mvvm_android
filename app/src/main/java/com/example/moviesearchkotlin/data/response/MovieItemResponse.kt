package com.example.moviesearchkotlin.data.response

import com.example.moviesearchkotlin.ui.domain.model.MovieItem

data class MovieResponse(
    val Search: List<MovieItemResponse>
)

fun MovieResponse.mapToDataModel(): List<MovieItem> = this.Search.map {
    it.mapToDataModel()
}

data class MovieItemResponse(
    val imdbID: String,
    val Poster: String?,
    val Title: String?,
    val Year: String?,
    val Rated: String?,
    val Released: String?,
    val Runtime: String?,
    val Genre: String?,
    val Director: String?,
    val Writer: String?,
    val Actors: String?,
    val Plot: String?,
    val Language: String?,
    val Country: String?,
    val Awards: String?,
    val imdbRating: String?,
    val imdbVotes: String?,
    val Type: String?,
    val DVD: String?,
    val BoxOffice: String?,
    val Production: String?,
    val Response: String?

)

fun MovieItemResponse.mapToDataModel(): MovieItem = MovieItem(
    movieId = imdbID,
    moviePosterUrl = Poster ?: "",
    movieTitle = Title ?: "",
    movieYear = Year ?: "",
    moviePlot = Plot ?: "",
    movieDirector =  Director ?: ""
)