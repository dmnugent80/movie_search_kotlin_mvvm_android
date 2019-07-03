package com.example.moviesearchkotlin.datasource.local

import com.example.moviesearchkotlin.api.ApiService
import com.example.moviesearchkotlin.ui.domain.model.MovieItem
import io.paperdb.Book
import io.paperdb.Paper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieLocalDataSource
@Inject constructor() {

    fun saveSearchCache(searchString: String, movieList: List<MovieItem>): Book? =
        Paper.book().write(searchString, movieList)

    fun loadCachedList(searchString: String): List<MovieItem> =
        Paper.book().read(searchString, arrayListOf())
}