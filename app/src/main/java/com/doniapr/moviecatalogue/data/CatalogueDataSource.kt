package com.doniapr.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.doniapr.moviecatalogue.data.source.local.entity.Movie
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow

interface CatalogueDataSource {
    fun getAllMovie(): LiveData<List<Movie>>

    fun getDetailMovie(id: String): LiveData<Movie>

    fun getAllTvShow(): LiveData<List<TvShow>>

    fun getDetailTvShow(id: String): LiveData<TvShow>

    fun getFavoriteMovie(): LiveData<PagedList<Movie>>

    fun getFavoriteTvShow(): LiveData<PagedList<TvShow>>

    fun getDetailFavoriteMovie(id: String): LiveData<Movie>

    fun getDetailFavoriteTvShow(id: String): LiveData<TvShow>

    fun addFavoriteMovie(movie: Movie)

    fun addFavoriteTvShow(tvShow: TvShow)

    fun unFavoriteMovie(movie: Movie)

    fun unFavoriteTvShow(tvShow: TvShow)

}