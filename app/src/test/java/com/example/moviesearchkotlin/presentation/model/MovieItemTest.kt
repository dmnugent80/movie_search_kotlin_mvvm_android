package com.example.moviesearchkotlin.presentation.model

import com.example.moviesearchkotlin.data.response.MovieItemResponse
import com.example.moviesearchkotlin.data.response.MovieResponse
import com.example.moviesearchkotlin.data.response.mapToDataModel
import com.example.moviesearchkotlin.movieItemResponseStub
import org.junit.Assert.assertTrue
import org.junit.Test

class MovieItemTest {

    @Test
    fun `map response to data model`() {

        val movieItemListStub = listOf(movieItemResponseStub)

        val movieResponse = MovieResponse(movieItemListStub)
        val movieItemResponse = movieResponse.Search.first()

        val movieItem = movieItemResponse.mapToDataModel()

        assertTrue(movieItemResponse.imdbID == movieItem.movieId)
        assertTrue(movieItemResponse.Poster == movieItem.moviePosterUrl)
        assertTrue(movieItemResponse.Title == movieItem.movieTitle)
        assertTrue(movieItemResponse.Year == movieItem.movieYear)
    }
}
