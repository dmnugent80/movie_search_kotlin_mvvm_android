package com.example.moviesearchkotlin.ui.domain.usecase

import com.example.moviesearchkotlin.ui.domain.repository.MovieRepository
import com.example.moviesearchkotlin.ui.domain.model.MovieItem
import io.reactivex.Observable

import io.reactivex.Single
import javax.inject.Inject

class GetMoviesUseCase
@Inject constructor(
    private val movieRepository: MovieRepository
) {
    fun execute(search: String): Single<List<MovieItem>> =
        movieRepository.get(search)

    fun getMovie(movieID: String): Observable<MovieItem> =
        movieRepository.getMovie(movieID)
}