package com.example.moviesearchkotlin.domain.usecase

import com.example.moviesearchkotlin.movieItemStub
import com.example.moviesearchkotlin.ui.domain.repository.MovieRepository
import com.example.moviesearchkotlin.ui.domain.usecase.GetMoviesUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetMoviesUseCaseTest {

    private lateinit var useCase: GetMoviesUseCase
    private val mockRepository: MovieRepository = mock()

    private val movieList = listOf(movieItemStub)
    private val throwable = Throwable()

    @Before
    fun setUp() {
        useCase = GetMoviesUseCase(mockRepository)
    }

    @Test
    fun `movie repository get success`() {
        // given
        whenever(mockRepository.get("star wars")).thenReturn(Single.just(movieList))

        // when
        val test = useCase.execute("star wars").test()

        // then
        verify(mockRepository).get("star wars")

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(movieList)
    }

    @Test
    fun `movie repository get failure`() {
        // given
        whenever(mockRepository.get("star wars")).thenReturn(Single.error(throwable))

        // when
        val test = useCase.execute("star wars").test()

        // then
        verify(mockRepository).get("star wars")

        test.assertNoValues()
        test.assertNotComplete()
        test.assertValueCount(0)
        test.assertError(throwable)
    }
}
