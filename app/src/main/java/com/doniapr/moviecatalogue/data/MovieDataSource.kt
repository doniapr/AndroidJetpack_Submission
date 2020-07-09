package com.doniapr.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.doniapr.moviecatalogue.data.source.local.Movie
import com.doniapr.moviecatalogue.data.source.local.Review

interface MovieDataSource {
    fun getNowPlayingMovie(): LiveData<List<Movie>>

    fun getPopularMovie(): LiveData<List<Movie>>

    fun getUpcomingMovie(): LiveData<List<Movie>>

    fun getSearchMovie(query: String): LiveData<List<Movie>>

    fun getDetailMovie(movieId: String): LiveData<Movie>

    fun getReviewMovie(movieId: String): LiveData<List<Review>>
}