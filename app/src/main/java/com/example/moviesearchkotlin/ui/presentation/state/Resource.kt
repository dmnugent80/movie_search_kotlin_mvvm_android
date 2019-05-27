package com.example.moviesearchkotlin.ui.presentation.state

data class Resource<out T> constructor(
    val state: ResourceState,
    val data: T? = null,
    val message: String? = null
)