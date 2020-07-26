package com.doniapr.moviecatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.doniapr.moviecatalogue.data.CatalogueRepository
import com.doniapr.moviecatalogue.data.source.local.entity.Movie
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observerMovie: Observer<PagedList<Movie>>

    @Mock
    private lateinit var observerTvShow: Observer<PagedList<TvShow>>

    @Mock
    private lateinit var pagedListMovie: PagedList<Movie>

    @Mock
    private lateinit var pagedListTvShow: PagedList<TvShow>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(catalogueRepository)
    }

    @Test
    fun getMovieDataList() {
        val dummy = pagedListMovie
        `when`(dummy.size).thenReturn(5)
        val movies = MutableLiveData<PagedList<Movie>>()
        movies.value = dummy

        `when`(catalogueRepository.getFavoriteMovie()).thenReturn(movies)
        val movieEntity = viewModel.getMovieDataList().value
        verify<CatalogueRepository>(catalogueRepository).getFavoriteMovie()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getMovieDataList().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummy)

    }

    @Test
    fun getTvShowDataList() {
        val dummy = pagedListTvShow
        `when`(dummy.size).thenReturn(5)
        val tvShows = MutableLiveData<PagedList<TvShow>>()
        tvShows.value = dummy

        `when`(catalogueRepository.getFavoriteTvShow()).thenReturn(tvShows)
        val tvShowEntity = viewModel.getTvShowDataList().value
        verify<CatalogueRepository>(catalogueRepository).getFavoriteTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(5, tvShowEntity?.size)

        viewModel.getTvShowDataList().observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummy)
    }
}