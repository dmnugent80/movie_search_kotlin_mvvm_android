package com.example.moviesearchkotlin.ui.domain.model

data class MovieItem(
    val movieId: String,
    val moviePosterUrl: String,
    val movieTitle: String
)

//fun MovieItem.mapToPresentation(): MovieItem = MovieItem(
//    movieId,
//    moviePosterUrl,
//    movieTitle
//)