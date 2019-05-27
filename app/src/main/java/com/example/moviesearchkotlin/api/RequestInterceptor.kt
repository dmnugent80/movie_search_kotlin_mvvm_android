package com.example.moviesearchkotlin.api

import android.util.Log
import com.example.moviesearchkotlin.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()
        val url = originalUrl.newBuilder()
            .addQueryParameter("apikey", "40dd46c2")
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}