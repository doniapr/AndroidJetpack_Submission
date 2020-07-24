package com.doniapr.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.doniapr.moviecatalogue.data.CatalogueRepository
import com.doniapr.moviecatalogue.data.source.local.entity.Movie

class DetailMovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getDetailMovie(id: String): LiveData<Movie> = catalogueRepository.getDetailMovie(id)

    fun checkIsFavorite(id: String): LiveData<Movie> = catalogueRepository.getDetailFavoriteMovie(id)

    fun setFavorite(movie: Movie) {
        catalogueRepository.addFavoriteMovie(movie)
    }

    fun setUnFavorite(movie: Movie){
        catalogueRepository.unFavoriteMovie(movie)
    }

}