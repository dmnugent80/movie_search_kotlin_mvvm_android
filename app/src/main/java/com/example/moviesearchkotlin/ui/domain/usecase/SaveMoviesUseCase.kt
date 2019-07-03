package com.example.moviesearchkotlin.ui.domain.usecase

import com.example.moviesearchkotlin.ui.domain.repository.MovieRepository
import com.example.moviesearchkotlin.ui.domain.model.MovieItem
import io.paperdb.Book

import javax.inject.Inject

class SaveMoviesUseCase
@Inject constructor(
    private val movieRepository: MovieRepository
) {
    fun saveResult(search: String, resultList: List<MovieItem>): Book? =
            movieRepository.saveSearchCache(search, resultList)

}