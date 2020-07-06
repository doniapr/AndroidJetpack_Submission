package com.doniapr.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.doniapr.moviecatalogue.data.Movie
import com.doniapr.moviecatalogue.data.TvShow
import com.doniapr.moviecatalogue.utils.GenerateData

class DetailViewModel : ViewModel() {
    fun getDetailMovie(title: String?): Movie {
        val movies = GenerateData.generateMovieData()
        val detailMovie = movies.filter {
            it.title == title
        }

        return detailMovie[0]
    }

    fun getDetailTvShow(title: String?): TvShow {
        val tvShows = GenerateData.generateTvShowData()
        val detailTvShow = tvShows.filter {
            it.title == title
        }

        return detailTvShow[0]
    }
}