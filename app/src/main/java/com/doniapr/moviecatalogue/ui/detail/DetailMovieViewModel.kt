package com.doniapr.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.doniapr.moviecatalogue.data.MovieRepository
import com.doniapr.moviecatalogue.data.source.local.Movie
import com.doniapr.moviecatalogue.data.source.local.Review

class DetailMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getDetailMovie(id: String): LiveData<Movie> = movieRepository.getDetailMovie(id)

    fun getReview(id: String): LiveData<List<Review>> = movieRepository.getReviewMovie(id)
}