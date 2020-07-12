package com.doniapr.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var id: String? = null,
    var title: String? = null,
    var overview: String? = null,
    var originalTitle: String? = null,
    var genres: String? = null,
    var releaseDate: String? = null,
    var runtime: String? = null,
    var tagline: String? = null,
    var status: String? = null,
    var voteAverage: String? = null,
    var voteCount: String? = null,
    var posterPath: String? = null,
    var backdropPath: String? = null
): Parcelable