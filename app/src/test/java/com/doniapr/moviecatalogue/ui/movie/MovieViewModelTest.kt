package com.doniapr.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.doniapr.moviecatalogue.data.CatalogueRepository
import com.doniapr.moviecatalogue.data.source.local.entity.Movie
import com.doniapr.moviecatalogue.ui.movie.MovieViewModel
import com.doniapr.moviecatalogue.utils.GenerateData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    @Before
    fun setUp(){
        viewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun getMovies(){
        val dummyMovies  = GenerateData.generateMovieData()
        val movies = MutableLiveData<List<Movie>>()
        movies.value = dummyMovies

        `when`(catalogueRepository.getAllMovie()).thenReturn(movies)
        val movieEntity = viewModel.getMovie().value
        verify<CatalogueRepository>(catalogueRepository).getAllMovie()
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

}