package com.doniapr.moviecatalogue.detail

import com.doniapr.moviecatalogue.ui.detail.DetailMovieViewModel
import com.doniapr.moviecatalogue.utils.GenerateData
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class DetailViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val movie = GenerateData.generateMovieData()[0]
    private val movieTitle = movie.title
    private val tvShow = GenerateData.generateTvShowData()[0]
    private val tvShowTitle = tvShow.title

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
    }

    @Test
    fun getDetailMovie() {
        val detailMovie = viewModel.getDetailMovie(movieTitle)
        assertNotNull(detailMovie)
        assertEquals(movie.title, detailMovie.title)
        assertEquals(movie.director, detailMovie.director)
        assertEquals(movie.genre, detailMovie.genre)
        assertEquals(movie.overview, detailMovie.overview)
        assertEquals(movie.year, detailMovie.year)
    }

    @Test
    fun getDetailTvShow() {
        val detailTvShow = viewModel.getDetailTvShow(tvShowTitle)
        assertNotNull(detailTvShow)
        assertEquals(tvShow.title, detailTvShow.title)
        assertEquals(tvShow.director, detailTvShow.director)
        assertEquals(tvShow.genre, detailTvShow.genre)
        assertEquals(tvShow.overview, detailTvShow.overview)
        assertEquals(tvShow.year, detailTvShow.year)
    }
}