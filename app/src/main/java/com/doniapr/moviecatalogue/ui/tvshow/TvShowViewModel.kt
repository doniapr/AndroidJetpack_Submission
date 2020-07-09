package com.doniapr.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.doniapr.moviecatalogue.data.TvShowRepository
import com.doniapr.moviecatalogue.data.source.local.TvShow

class TvShowViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {

    fun getAiringTodayTvShow(): LiveData<List<TvShow>> = tvShowRepository.getAiringTodayTvShow()

    fun getOnAirTvShow(): LiveData<List<TvShow>> = tvShowRepository.getOnAirTvShow()

    fun getPopularTvShow(): LiveData<List<TvShow>> = tvShowRepository.getPopularTvShow()

}