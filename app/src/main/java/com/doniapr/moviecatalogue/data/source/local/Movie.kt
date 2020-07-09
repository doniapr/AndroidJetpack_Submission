package com.doniapr.moviecatalogue.data.source.local

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("original_title")
    var originalTitle: String? = null,
    @SerializedName("genres")
    var genres: List<Genre>? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null,
    @SerializedName("runtime")
    var runtime: String? = null,
    @SerializedName("tagline")
    var tagline: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("vote_average")
    var voteAverage: String? = null,
    @SerializedName("vote_count")
    var voteCount: String? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null
): Parcelable