package com.example.moviesearchkotlin

import android.app.Application
import javax.inject.Inject

import android.app.Activity

import com.example.moviesearchkotlin.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector


class App : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent
            .create()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}