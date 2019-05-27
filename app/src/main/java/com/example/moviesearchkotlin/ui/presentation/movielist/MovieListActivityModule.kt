package com.example.moviesearchkotlin.ui.presentation.movielist

import com.example.moviesearchkotlin.ui.domain.repository.MovieRepository
import com.example.moviesearchkotlin.ui.domain.usecase.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import dagger.android.ContributesAndroidInjector



@Module
class MovieListActivityModule {

    @Provides
    @Singleton
    @Named("movieListViewModel")
    fun provideMovieListViewModel(): MovieListViewModel = MovieListViewModel(provideGetMoviesUseCase())

    @Provides
    @Singleton
    @Named("moviesUseCase")
    fun provideGetMoviesUseCase(): GetMoviesUseCase = GetMoviesUseCase(provideMoviesRepository())

    @Provides
    @Singleton
    @Named("moviesRepository")
    fun provideMoviesRepository(): MovieRepository = MovieRepository()
}