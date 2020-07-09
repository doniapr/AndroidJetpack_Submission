package com.doniapr.moviecatalogue.data.source.remote.response

import com.doniapr.moviecatalogue.data.source.local.Review
import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("results")
    var reviews: List<Review>? = null,
    @SerializedName("page")
    var page: Int? = 0,
    @SerializedName("total_pages")
    var totalPages: Int? = 0,
    @SerializedName("total_results")
    var totalResults: Int? = 0

)