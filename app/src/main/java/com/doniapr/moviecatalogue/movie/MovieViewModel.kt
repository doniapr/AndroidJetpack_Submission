package com.doniapr.moviecatalogue.movie

import androidx.lifecycle.ViewModel
import com.doniapr.moviecatalogue.data.Movie
import com.doniapr.moviecatalogue.utils.GenerateData

class MovieViewModel : ViewModel(){
    fun getMovie(): List<Movie> = GenerateData.generateMovieData()
}