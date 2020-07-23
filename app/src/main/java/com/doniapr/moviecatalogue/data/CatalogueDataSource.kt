package com.doniapr.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.doniapr.moviecatalogue.data.source.local.entity.Movie
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow
import com.doniapr.moviecatalogue.vo.Resource

interface CatalogueDataSource {
    fun getAllMovie(): LiveData<Resource<List<Movie>>>

    fun getDetailMovie(id: String): LiveData<Resource<Movie>>

    fun getAllTvShow(): LiveData<Resource<List<TvShow>>>

    fun getDetailTvShow(id: String): LiveData<Resource<TvShow>>

    fun setMovieFavorite(movie: Movie)

    fun setTvShowFavorite(tvShow: TvShow)

}