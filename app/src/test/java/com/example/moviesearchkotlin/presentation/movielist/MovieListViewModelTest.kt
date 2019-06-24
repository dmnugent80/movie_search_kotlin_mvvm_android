package com.example.moviesearchkotlin.presentation.movielist

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesearchkotlin.movieItemStub
import com.example.moviesearchkotlin.ui.domain.usecase.GetMoviesUseCase
import com.example.moviesearchkotlin.ui.presentation.movielist.MovieListViewModel
import com.example.moviesearchkotlin.ui.presentation.state.Resource
import com.example.moviesearchkotlin.ui.presentation.state.ResourceState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MovieListViewModelTest {
    private lateinit var movieListViewModel: MovieListViewModel

    private val mockGetMoviesUseCase: GetMoviesUseCase = mock()

    private val movieListStub = listOf(movieItemStub)
    private val throwable = Throwable()

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        movieListViewModel = MovieListViewModel(mockGetMoviesUseCase)
    }

    @Test
    fun `get movie item list succeeds`() {

        // given
        whenever(mockGetMoviesUseCase.execute("star wars")).thenReturn(Single.just(movieListStub))

        // when
        movieListViewModel.get("star wars")

        // then
        verify(mockGetMoviesUseCase).execute("star wars")
        assertEquals(
            Resource(ResourceState.SUCCESS, movieListStub, null),
            movieListViewModel.movieListData.value
        )
    }

    @Test
    fun `get movie item list fails`() {

        // given
        whenever(mockGetMoviesUseCase.execute("star wars")).thenReturn(Single.error(throwable))

        // when
        movieListViewModel.get("star wars")

        // then
        verify(mockGetMoviesUseCase).execute("star wars")
        assertEquals(
            Resource(ResourceState.ERROR, null, throwable.message),
            movieListViewModel.movieListData.value
        )
    }
}
