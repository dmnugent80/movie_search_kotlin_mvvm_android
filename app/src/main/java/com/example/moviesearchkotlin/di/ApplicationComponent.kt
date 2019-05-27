package com.example.moviesearchkotlin.di

import com.example.moviesearchkotlin.App
import com.example.moviesearchkotlin.common.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AndroidInjectionModule::class,
    AndroidModule::class,
    MovieListModule::class,
    ActivityModule::class,
    ViewModelModule::class
))
interface ApplicationComponent {
    fun inject(app: App)
}