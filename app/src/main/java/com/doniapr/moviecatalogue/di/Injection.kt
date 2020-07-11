package com.doniapr.moviecatalogue.di

import com.doniapr.moviecatalogue.data.MovieRepository
import com.doniapr.moviecatalogue.data.TvShowRepository
import com.doniapr.moviecatalogue.data.source.remote.RemoteDataSource

object Injection {
    fun provideMovieRepository(): MovieRepository {
        val remoteDataSource = RemoteDataSource()

        return MovieRepository.getInstance(remoteDataSource)
    }

    fun provideTvShowRepository(): TvShowRepository {
        val remoteDataSource = RemoteDataSource()

        return TvShowRepository.getInstance(remoteDataSource)
    }
}