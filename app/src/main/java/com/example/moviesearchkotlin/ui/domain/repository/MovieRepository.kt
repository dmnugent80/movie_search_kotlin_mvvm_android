package com.example.moviesearchkotlin.ui.domain.repository

import com.example.moviesearchkotlin.datasource.remote.MovieRemoteDataSource
import com.example.moviesearchkotlin.ui.domain.model.MovieItem
import io.paperdb.Book
import io.paperdb.Paper
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository
@Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) {
    fun get(search: String): Single<List<MovieItem>> =
        movieRemoteDataSource.get(search)

    fun saveSearchCache(searchString: String, movieList: List<MovieItem>): Book? =
            Paper.book().write(searchString, movieList)

    fun loadCachedList(searchString: String): List<MovieItem> =
        Paper.book().read(searchString, arrayListOf())
}