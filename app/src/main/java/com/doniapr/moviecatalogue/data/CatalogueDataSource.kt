package com.doniapr.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.doniapr.moviecatalogue.data.source.local.entity.Movie
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow

interface CatalogueDataSource {
    fun getAllMovie(): LiveData<List<Movie>>

    fun getDetailMovie(id: String): LiveData<Movie>

    fun getAllTvShow(): LiveData<List<TvShow>>

    fun getDetailTvShow(id: String): LiveData<TvShow>
}