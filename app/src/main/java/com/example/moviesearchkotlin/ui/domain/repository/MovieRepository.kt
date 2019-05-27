package com.example.moviesearchkotlin.ui.domain.repository

import com.example.moviesearchkotlin.api.ApiService
import com.example.moviesearchkotlin.data.response.MovieItemResponse
import com.example.moviesearchkotlin.datasource.MovieRemoteDataSource
import com.example.moviesearchkotlin.ui.domain.model.MovieItem
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository
@Inject constructor(
    val movieRemoteDataSource: MovieRemoteDataSource
) {

    fun get(search: String): Single<List<MovieItem>> =
        movieRemoteDataSource.get(search)

//    {
//        var returnList: List<MovieItemResponse>? = null
//        val service = Retrofit.Builder()
//            .baseUrl("https://api.github.com/")
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//
//        service.getMovies("Evin1-")
//            .enqueue(object : Callback<List<MovieItemResponse>> {
//                override fun onResponse(call: Call<List<MovieItemResponse>>, response: Response<List<MovieItemResponse>>) {
//                    response.body()?.forEach { println("TAG_: $it") }
//                    returnList = response.body()
//                }
//
//                override fun onFailure(call: Call<List<MovieItemResponse>>, t: Throwable) = t.printStackTrace()
//            })
//        return returnList
//    }
//            = Single.just(
//        listOf(
//            MovieItem(
//                "1",
//                "https://avatars2.githubusercontent.com/u/10395954?s=400&u=aa818ddf12e440553b87c377535975e126ca0f78&v=4",
//                "ExampleMovie"
//            )
//        )
//    )

}