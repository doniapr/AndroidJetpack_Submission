package com.doniapr.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    var id: String? = null,
    var title: String? = null,
    var overview: String? = null,
    var originalTitle: String? = null,
    var genres: String? = null,
    var firstAirDate: String? = null,
    var episodeRunTime: List<String>? = null,
    var inProduction: Boolean? = null,
    var status: String? = null,
    var voteAverage: String? = null,
    var voteCount: String? = null,
    var posterPath: String? = null,
    var backdropPath: String? = null,
    var numberOfEpisodes: String? = null,
    var numberOfSeasons: String? = null
): Parcelable