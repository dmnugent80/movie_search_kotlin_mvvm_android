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
    val Year: String?
)

fun MovieItemResponse.mapToDataModel(): MovieItem = MovieItem(
    imdbID,
    Poster ?: "",
    Title ?: "",
    Year ?: ""
)