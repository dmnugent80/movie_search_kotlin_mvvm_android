package com.example.moviesearchkotlin

import android.app.Application
import javax.inject.Inject

import android.app.Activity

import com.example.moviesearchkotlin.di.DaggerApplicationComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector
import io.paperdb.Paper

class App : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
        DaggerApplicationComponent
            .create()
            .inject(this)

        Paper.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}