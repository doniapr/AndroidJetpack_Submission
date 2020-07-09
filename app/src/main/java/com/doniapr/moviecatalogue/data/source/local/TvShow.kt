package com.doniapr.moviecatalogue.data.source.local

import com.google.gson.annotations.SerializedName

data class TvShow(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var title: String? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("original_name")
    var originalTitle: String? = null,
    @SerializedName("genres")
    var genres: List<Genre>? = null,
    @SerializedName("first_air_date")
    var firstAirDate: String? = null,
    @SerializedName("episode_run_time")
    var episodeRunTime: List<String>? = null,
    @SerializedName("in_production")
    var inProduction: Boolean? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("vote_average")
    var voteAverage: String? = null,
    @SerializedName("vote_count")
    var voteCount: String? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("number_of_episodes")
    var numberOfEpisodes: String? = null,
    @SerializedName("number_of_seasons")
    var numberOfSeasons: String? = null
)