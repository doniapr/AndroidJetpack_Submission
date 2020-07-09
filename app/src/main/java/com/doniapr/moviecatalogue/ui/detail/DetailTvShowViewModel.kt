package com.doniapr.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.doniapr.moviecatalogue.data.TvShowRepository

class DetailTvShowViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {
    fun getDetailTvShow(id: String) = tvShowRepository.getDetailTvShow(id)
}