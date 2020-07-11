package com.doniapr.moviecatalogue.data

import com.doniapr.moviecatalogue.data.source.remote.RemoteDataSource
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class MovieRepositoryTest {

    private lateinit var movieRepository: MovieRepository
    private val remote = mock(RemoteDataSource::class.java)


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        movieRepository = MovieRepository(remote)
    }

    @Test
    fun getNowPlayingMovie() {
        
    }

    @Test
    fun getPopularMovie() {
    }

    @Test
    fun getUpcomingMovie() {
    }

    @Test
    fun getSearchMovie() {
    }

    @Test
    fun getDetailMovie() {
    }

    @Test
    fun getReviewMovie() {
    }
}