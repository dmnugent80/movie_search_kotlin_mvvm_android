package com.example.moviesearchkotlin.ui.domain.repository

import android.util.Log
import com.example.moviesearchkotlin.datasource.local.MovieLocalDataSource
import com.example.moviesearchkotlin.datasource.remote.MovieRemoteDataSource
import com.example.moviesearchkotlin.ui.domain.model.MovieItem
import io.paperdb.Book
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository
@Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) {
    fun get(search: String): Single<List<MovieItem>> {
        val cachedList = movieLocalDataSource.loadCachedList(search)
        return when {
            cachedList.isNotEmpty() -> {
                Log.d("test", "<><><> cached list not empty: " + cachedList)
                Single.just(cachedList)
            }
            else -> {
                Log.d("test", "<><><> cached list empty, calling get")
                movieRemoteDataSource.getMovies(search)
            }
        }
    }

    fun getMovie(movieId: String): Observable<MovieItem> =  movieRemoteDataSource.getMovie(movieId)

    fun saveSearchCache(searchString: String, movieList: List<MovieItem>): Book? =
        movieLocalDataSource.saveSearchCache(searchString, movieList)
}