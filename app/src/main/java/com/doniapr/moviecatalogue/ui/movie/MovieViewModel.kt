package com.doniapr.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.doniapr.moviecatalogue.data.CatalogueRepository
import com.doniapr.moviecatalogue.data.source.local.entity.Movie

class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getMovie(): LiveData<List<Movie>> = catalogueRepository.getAllMovie()
}