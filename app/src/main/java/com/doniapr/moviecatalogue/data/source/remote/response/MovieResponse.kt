package com.doniapr.moviecatalogue.data.source.remote.response

import com.doniapr.moviecatalogue.data.source.local.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("results")
    var movies: List<Movie>? = null,
    @SerializedName("page")
    var page: Int? = 0,
    @SerializedName("total_results")
    var totalResult: Int? = 0,
    @SerializedName("total_pages")
    var totalPages: Int? = 0
)