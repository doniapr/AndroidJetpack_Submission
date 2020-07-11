package com.doniapr.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.doniapr.moviecatalogue.data.MovieRepository
import com.doniapr.moviecatalogue.data.source.local.Movie

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getNowPlayingMovie(): LiveData<List<Movie>> = movieRepository.getNowPlayingMovie()

    fun getPopularMovie(): LiveData<List<Movie>> = movieRepository.getPopularMovie()

    fun getUpcomingMovie(): LiveData<List<Movie>> = movieRepository.getUpcomingMovie()

}