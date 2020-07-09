package com.doniapr.moviecatalogue.data.source.remote.response

import com.doniapr.moviecatalogue.data.source.local.TvShow
import com.google.gson.annotations.SerializedName

data class TvShowResponse (
    @SerializedName("page")
    var page: String? = null,
    @SerializedName("results")
    var tvShow: List<TvShow>? = null,
    @SerializedName("total_results")
    var totalResults: String? = null,
    @SerializedName("total_pages")
    var totalPages: String? = null
)