package com.example.moviesearchkotlin.ui.domain.usecase

import android.util.Log
import com.example.moviesearchkotlin.ui.domain.repository.MovieRepository
import com.example.moviesearchkotlin.ui.domain.model.MovieItem
import io.paperdb.Book

import io.reactivex.Single
import javax.inject.Inject

class GetMoviesUseCase
@Inject constructor(
    private val movieRepository: MovieRepository
) {
    fun execute(search: String): Single<List<MovieItem>> {
        val cachedList = movieRepository.loadCachedList(search)
        return when {
            cachedList.isNotEmpty() -> {
                Log.d("test", "<><><> cached list not empty: " + cachedList)
                Single.just(cachedList)
            }
            else -> {
                Log.d("test", "<><><> cached list empty, calling get")
                movieRepository.get(search)
            }
        }
    }
}