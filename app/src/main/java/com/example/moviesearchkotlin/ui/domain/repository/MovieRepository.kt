package com.example.moviesearchkotlin.ui.domain.repository

import com.example.moviesearchkotlin.datasource.MovieRemoteDataSource
import com.example.moviesearchkotlin.ui.domain.model.MovieItem
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository
@Inject constructor(
    val movieRemoteDataSource: MovieRemoteDataSource
) {

    fun get(search: String): Single<List<MovieItem>> =
        movieRemoteDataSource.get(search)
}