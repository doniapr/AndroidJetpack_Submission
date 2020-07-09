package com.doniapr.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.doniapr.moviecatalogue.data.source.local.Review
import com.doniapr.moviecatalogue.data.source.local.TvShow

interface TvShowDataSource {
    fun getOnAirTvShow(): LiveData<List<TvShow>>

    fun getAiringTodayTvShow(): LiveData<List<TvShow>>

    fun getPopularTvShow(): LiveData<List<TvShow>>

    fun getSearchTvShow(query: String): LiveData<List<TvShow>>

    fun getDetailTvShow(id: String): LiveData<TvShow>

    fun getReviewTvShow(id: String): LiveData<List<Review>>


}