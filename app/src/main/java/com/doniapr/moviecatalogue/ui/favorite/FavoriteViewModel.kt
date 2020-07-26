package com.doniapr.moviecatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.doniapr.moviecatalogue.data.CatalogueRepository
import com.doniapr.moviecatalogue.data.source.local.entity.Movie
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow

class FavoriteViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getMovieDataList(): LiveData<PagedList<Movie>> = catalogueRepository.getFavoriteMovie()

    fun getTvShowDataList(): LiveData<PagedList<TvShow>> = catalogueRepository.getFavoriteTvShow()

}