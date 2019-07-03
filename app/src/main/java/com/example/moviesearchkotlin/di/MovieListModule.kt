package com.example.moviesearchkotlin.di

import android.support.annotation.NonNull
import com.example.moviesearchkotlin.api.ApiService
import com.example.moviesearchkotlin.api.RequestInterceptor
import com.example.moviesearchkotlin.datasource.remote.MovieRemoteDataSource
import com.example.moviesearchkotlin.ui.domain.repository.MovieRepository
import com.example.moviesearchkotlin.ui.domain.usecase.GetMoviesUseCase
import com.example.moviesearchkotlin.ui.presentation.movielist.MovieListViewModel
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class MovieListModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@NonNull okHttpClient: OkHttpClient = provideHttpClient()): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(@NonNull retrofit: Retrofit = provideRetrofit()): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Named("movieListViewModel")
    fun provideMovieListViewModel(): MovieListViewModel =
        MovieListViewModel(provideGetMoviesUseCase())

    @Provides
    @Named("moviesUseCase")
    fun provideGetMoviesUseCase(): GetMoviesUseCase = GetMoviesUseCase(provideMoviesRepository())

    @Provides
    @Named("moviesRepository")
    fun provideMoviesRepository(): MovieRepository = MovieRepository(provideMovieRemoteDataSource())

    @Provides
    @Named("movieRemoteDataSource")
    fun provideMovieRemoteDataSource(): MovieRemoteDataSource =
        MovieRemoteDataSource(provideApiService())

}