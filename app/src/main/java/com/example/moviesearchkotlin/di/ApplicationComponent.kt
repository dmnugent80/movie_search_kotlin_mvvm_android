package com.example.moviesearchkotlin.di

import com.example.moviesearchkotlin.App
import com.example.moviesearchkotlin.ui.presentation.movielist.MovieListActivityModule
import com.example.moviesearchkotlin.ui.presentation.movielist.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AndroidInjectionModule::class,
    AndroidModule::class,
    MovieListActivityModule::class,
    ActivityModule::class,
    ViewModelModule::class
))
interface ApplicationComponent {
    fun inject(app: App)
}