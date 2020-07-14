package com.doniapr.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.doniapr.moviecatalogue.data.CatalogueRepository
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow
import com.doniapr.moviecatalogue.utils.GenerateData
import com.nhaarman.mockitokotlin2.verify
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
class DetailTvShowViewModelTest {

    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTvShow = GenerateData.generateTvShowData()[0]
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<TvShow>

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(catalogueRepository)
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = MutableLiveData<TvShow>()
        tvShow.value = dummyTvShow

        Mockito.`when`(catalogueRepository.getDetailTvShow(tvShowId!!)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getDetailTvShow(tvShowId).value
        verify(catalogueRepository).getDetailTvShow(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.title, tvShowEntity?.title)
        assertEquals(dummyTvShow.overview, tvShowEntity?.overview)
        assertEquals(dummyTvShow.genres, tvShowEntity?.genres)
        assertEquals(dummyTvShow.status, tvShowEntity?.status)
        assertEquals(dummyTvShow.firstAirDate, tvShowEntity?.firstAirDate)

        viewModel.getDetailTvShow(tvShowId).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShow)
    }
}