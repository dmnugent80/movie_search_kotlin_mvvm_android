package com.example.moviesearchkotlin.ui.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieItem(
    val movieId: String,
    val moviePosterUrl: String,
    val movieTitle: String,
    val movieYear: String,
    val moviePlot: String,
    val movieDirector: String
) : Parcelable
