package com.example.moviesearchkotlin

import com.example.moviesearchkotlin.data.response.MovieItemResponse
import com.example.moviesearchkotlin.ui.domain.model.MovieItem

val movieItemResponseStub = MovieItemResponse(
    imdbID = "1",
    Poster = "www.example.com/1.jpg",
    Title = "Title",
    Year = "2019",
    Rated = "",
    Released = "",
    Runtime = "",
    Genre = "",
    Director = "",
    Writer = "",
    Actors = "",
    Plot = "",
    Language = "",
    Country = "",
    Awards = "",
    imdbRating = "",
    imdbVotes = "",
    Type = "",
    DVD = "",
    BoxOffice = "",
    Production = "",
    Response = ""
)

val movieItemStub = MovieItem(
    "1",
    "www.example.com/1.jpg",
    "Title",
    "2019"
)