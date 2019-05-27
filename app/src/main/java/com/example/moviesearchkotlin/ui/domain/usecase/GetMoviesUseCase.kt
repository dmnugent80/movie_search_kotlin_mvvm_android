package com.example.moviesearchkotlin.ui.domain.usecase

import com.example.moviesearchkotlin.ui.domain.repository.MovieRepository
import com.example.moviesearchkotlin.ui.domain.model.MovieItem

import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMoviesUseCase
@Inject constructor(
    private val movieRepository: MovieRepository
) {
    fun execute(): Single<List<MovieItem>> {
        return movieRepository.get()
    }
}