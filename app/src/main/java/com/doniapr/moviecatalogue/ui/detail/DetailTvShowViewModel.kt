package com.doniapr.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.doniapr.moviecatalogue.data.CatalogueRepository
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow

class DetailTvShowViewModel(private val catalogueRepository: CatalogueRepository): ViewModel(){
    fun getDetailTvShow(id: String): LiveData<TvShow> = catalogueRepository.getDetailTvShow(id)
}