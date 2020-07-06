package com.doniapr.moviecatalogue.movie

import com.doniapr.moviecatalogue.ui.movie.MovieViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp(){
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovies(){
        val movies = viewModel.getMovie()
        assertNotNull(movies)
        assertEquals(19, movies.size)
    }

}