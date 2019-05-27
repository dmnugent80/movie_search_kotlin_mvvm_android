package com.example.moviesearchkotlin.api

import com.example.moviesearchkotlin.data.response.MovieItemResponse
import com.example.moviesearchkotlin.data.response.MovieResponse

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET(".")
    fun getMovieResponse(): Single<List<MovieItemResponse>>

    @GET(".")
    fun getMovies(@Query("s") search: String): Single<MovieResponse>
}