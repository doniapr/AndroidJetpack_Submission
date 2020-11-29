package com.doniapr.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.doniapr.moviecatalogue.data.CatalogueRepository
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow
import com.doniapr.moviecatalogue.utils.GenerateData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<TvShow>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getTvShow() {
        val dummyTvShow = GenerateData.generateTvShowData()
        val tvShows = MutableLiveData<List<TvShow>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(catalogueRepository.getAllTvShow()).thenReturn(tvShows)
        val tvShowEntity = viewModel.getTvShow().value
        Mockito.verify<CatalogueRepository>(catalogueRepository).getAllTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(20, tvShowEntity?.size)

        viewModel.getTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShow)
    }
}