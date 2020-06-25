package com.doniapr.moviecatalogue.detail

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun getDetailMovie() {
        val movie = viewModel.getDetailMovie("Aquaman")
        assertNotNull(movie)
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = viewModel.getDetailTvShow("Arrow")
        assertNotNull(tvShow)
    }
}