package com.example.moviesearchkotlin.ui.presentation.movielist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.moviesearchkotlin.ui.domain.usecase.GetMoviesUseCase
import com.example.moviesearchkotlin.ui.presentation.state.Resource
import com.example.moviesearchkotlin.ui.presentation.extensions.setError
import com.example.moviesearchkotlin.ui.presentation.extensions.setLoading
import com.example.moviesearchkotlin.ui.presentation.extensions.setSuccess
import com.example.moviesearchkotlin.ui.domain.model.MovieItem

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieListViewModel
@Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    val movieListData = MutableLiveData<Resource<List<MovieItem>>>()
    private val compositeDisposable = CompositeDisposable()

    fun get(search: String) =
        compositeDisposable.add(
            getMoviesUseCase.execute(search)
            .doOnSubscribe { movieListData.setLoading() }
            .subscribeOn(Schedulers.io())
            .subscribe(
                { movieListData.setSuccess(it) },
                { movieListData.setError(it.message) }
            )
        )

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }
}

