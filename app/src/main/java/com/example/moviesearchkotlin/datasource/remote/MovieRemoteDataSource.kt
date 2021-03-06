package com.example.moviesearchkotlin.datasource.remote

import com.example.moviesearchkotlin.api.ApiService
import com.example.moviesearchkotlin.data.response.mapToDataModel
import com.example.moviesearchkotlin.ui.domain.model.MovieItem
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource
@Inject constructor(
    private val api: ApiService
) {

    fun getMovies(search: String): Single<List<MovieItem>> {
        return api.getMovies(search)
            .map { it.mapToDataModel() }
    }

    fun getMovie(movieId: String): Observable<MovieItem> {
        return api.getMovie(movieId)
            .map { it.mapToDataModel()}

    }
}
