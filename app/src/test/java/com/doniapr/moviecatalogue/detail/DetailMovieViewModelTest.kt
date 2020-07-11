package com.doniapr.moviecatalogue.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.doniapr.moviecatalogue.data.MovieRepository
import com.doniapr.moviecatalogue.data.source.local.Movie
import com.doniapr.moviecatalogue.ui.detail.DetailMovieViewModel
import com.doniapr.moviecatalogue.utils.GenerateData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Movie>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = DetailMovieViewModel(movieRepository)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<Movie>()
        val movieId = "508439"

//        `when`(movieRepository.getDetailMovie(movieId)).thenReturn(movie)
        val movieDetail = viewModel.getDetailMovie(movieId).value
        verify<MovieRepository>(movieRepository).getDetailMovie(movieId)
        assertNotNull(movieDetail)

        viewModel.getDetailMovie(movieId).observeForever(observer)
        verify(observer).onChanged(movieDetail)
    }
}