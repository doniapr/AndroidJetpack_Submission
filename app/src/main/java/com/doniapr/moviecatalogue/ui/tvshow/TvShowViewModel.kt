package com.doniapr.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.doniapr.moviecatalogue.data.TvShow
import com.doniapr.moviecatalogue.utils.GenerateData

class TvShowViewModel : ViewModel() {
    fun getTvShow(): List<TvShow> = GenerateData.generateTvShowData()
}