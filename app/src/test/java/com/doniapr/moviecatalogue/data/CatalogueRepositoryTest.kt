package com.doniapr.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.doniapr.moviecatalogue.data.source.remote.RemoteDataSource
import com.doniapr.moviecatalogue.utils.GenerateData
import com.doniapr.moviecatalogue.utils.LiveDataTestUtil

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class CatalogueRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val catalogueRepository = FakeCatalogueRepository(remote)

    private val dummyMovie = GenerateData.generateRemoteMovieData()
    private val movie = dummyMovie[0]
    private val movieId = dummyMovie[0].id

    private val dummyTvShow = GenerateData.generateRemoteTvShowData()
    private val tvShow = dummyTvShow[0]
    private val tvShowId = dummyTvShow[0].id

    @Test
    fun getAllMovie(){
        doAnswer {invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMovieReceived(dummyMovie)
            null
        }.`when`(remote).getAllMovie(any())
        val movieEntity = LiveDataTestUtil.getValue(catalogueRepository.getAllMovie())
        verify(remote).getAllMovie(any())
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.size.toLong(), movieEntity.size.toLong())
    }

    @Test
    fun getDetailMovie(){
        doAnswer {invocation ->
            (invocation.arguments[0] as RemoteDataSource.GetDetailMovie).onMovieReceived(movie)
            null
        }.`when`(remote).getDetailMovie(eq(movieId!!), any())

        val data = catalogueRepository.getDetailMovie(movieId)
        val movieEntity = LiveDataTestUtil.getValue(data)
        verify(remote).getDetailMovie(eq(movieId), any())

        assertNotNull(movieEntity)
        assertEquals(movie.title, movieEntity.title)
        assertEquals(movie.overview, movieEntity.overview)
    }

    @Test
    fun getAllTvShow(){
        doAnswer {invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onAllTvShowReceived(dummyTvShow)
            null
        }.`when`(remote).getAllTvShow(any())
        val tvShowEntity = LiveDataTestUtil.getValue(catalogueRepository.getAllTvShow())
        verify(remote).getAllTvShow(any())
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.size.toLong(), tvShowEntity.size.toLong())
    }

    @Test
    fun getDetailTvShow(){
        doAnswer {invocation ->
            (invocation.arguments[0] as RemoteDataSource.GetDetailTvShow).onTvShowReceived(tvShow)
            null
        }.`when`(remote).getDetailTvShow(eq(tvShowId!!), any())

        val tvShowEntity = LiveDataTestUtil.getValue(catalogueRepository.getDetailTvShow(tvShowId))
        verify(remote).getDetailTvShow(eq(tvShowId), any())

        assertNotNull(tvShowEntity)
        assertEquals(tvShow.title, tvShowEntity.title)
        assertEquals(tvShow.overview, tvShowEntity.overview)
    }
}