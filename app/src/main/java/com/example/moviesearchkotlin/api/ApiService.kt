package com.example.moviesearchkotlin.api

import com.example.moviesearchkotlin.data.response.MovieItemResponse
import com.example.moviesearchkotlin.data.response.MovieResponse
import io.reactivex.Observable

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    fun getMovies(@Query("s") search: String): Single<MovieResponse>

    @GET(".")
    fun getMovie(@Query("i") search: String): Observable<MovieItemResponse>
}