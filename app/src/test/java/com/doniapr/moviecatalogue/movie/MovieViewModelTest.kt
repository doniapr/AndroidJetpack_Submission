package com.doniapr.moviecatalogue.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.doniapr.moviecatalogue.data.MovieRepository
import com.doniapr.moviecatalogue.data.source.local.Movie
import com.doniapr.moviecatalogue.ui.movie.MovieViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    @Before
    fun setUp(){
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getNowPlayingMovies(){
        val movies = viewModel.getNowPlayingMovie().value
        assertNotNull(movies)

        viewModel.getNowPlayingMovie().observeForever(observer)
        verify(observer).onChanged(movies)
    }

}