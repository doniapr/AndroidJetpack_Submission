package com.doniapr.moviecatalogue.detail

import androidx.lifecycle.ViewModel
import com.doniapr.moviecatalogue.data.Movie
import com.doniapr.moviecatalogue.data.TvShow
import com.doniapr.moviecatalogue.utils.GenerateData

class DetailViewModel : ViewModel(){
    fun getDetailMovie(title: String): Movie = GenerateData.generateDetailMovieData(title)
    fun getDetailTvShow(title: String): TvShow = GenerateData.generateDetailTvShowData(title)
}