package com.doniapr.moviecatalogue.tvshow

import com.doniapr.moviecatalogue.movie.MovieViewModel
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShow() {
        val tvShows = viewModel.getTvShow()
        assertNotNull(tvShows)
        assertEquals(12, tvShows.size)
    }
}