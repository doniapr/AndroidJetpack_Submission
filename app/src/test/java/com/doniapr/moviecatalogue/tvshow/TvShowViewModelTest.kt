package com.doniapr.moviecatalogue.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.doniapr.moviecatalogue.data.MovieRepository
import com.doniapr.moviecatalogue.data.TvShowRepository
import com.doniapr.moviecatalogue.data.source.local.Movie
import com.doniapr.moviecatalogue.data.source.local.TvShow
import com.doniapr.moviecatalogue.ui.tvshow.TvShowViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mock

class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @Mock
    private lateinit var observer: Observer<List<TvShow>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun getTvShow() {
        val tvShows = viewModel.getAiringTodayTvShow().value
        assertNotNull(tvShows)

        viewModel.getAiringTodayTvShow().observeForever(observer)
        verify(observer).onChanged(tvShows)
    }
}