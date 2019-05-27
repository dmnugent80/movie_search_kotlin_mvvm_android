package com.example.moviesearchkotlin.ui.domain.repository

import com.example.moviesearchkotlin.ui.domain.model.MovieItem
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository
@Inject constructor() {

    fun get(): Single<List<MovieItem>>
            = Single.just(
        listOf(
            MovieItem(
                "1",
                "https://avatars2.githubusercontent.com/u/10395954?s=400&u=aa818ddf12e440553b87c377535975e126ca0f78&v=4",
                "ExampleMovie"
            )
        )
    )

}