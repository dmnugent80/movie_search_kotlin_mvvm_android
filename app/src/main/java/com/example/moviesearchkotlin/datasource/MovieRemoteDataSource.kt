package com.example.moviesearchkotlin.datasource

import com.example.moviesearchkotlin.api.ApiService
import com.example.moviesearchkotlin.data.response.mapToDataModel
import com.example.moviesearchkotlin.ui.domain.model.MovieItem
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteDataSource
@Inject constructor(
    private val api: ApiService
) {

    fun get(search: String): Single<List<MovieItem>> {
        return api.getMovies(search)
            .map { it.mapToDataModel() }
    }
}
