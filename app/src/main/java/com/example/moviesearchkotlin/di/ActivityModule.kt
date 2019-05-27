package com.example.moviesearchkotlin.di

import com.example.moviesearchkotlin.ui.presentation.movielist.MovieListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMovieListActivity(): MovieListActivity
}