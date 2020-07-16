package com.doniapr.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.doniapr.moviecatalogue.data.CatalogueRepository
import com.doniapr.moviecatalogue.data.source.local.entity.Movie
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
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = GenerateData.generateMovieData()[0]
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Movie>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(catalogueRepository)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<Movie>()
        movie.value = dummyMovie

        `when`(catalogueRepository.getDetailMovie(movieId!!)).thenReturn(movie)
        val movieEntity = viewModel.getDetailMovie(movieId).value
        verify(catalogueRepository).getDetailMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.title, movieEntity?.title)
        assertEquals(dummyMovie.overview, movieEntity?.overview)
        assertEquals(dummyMovie.genres, movieEntity?.genres)
        assertEquals(dummyMovie.status, movieEntity?.status)
        assertEquals(dummyMovie.releaseDate, movieEntity?.releaseDate)

        viewModel.getDetailMovie(movieId).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }
}