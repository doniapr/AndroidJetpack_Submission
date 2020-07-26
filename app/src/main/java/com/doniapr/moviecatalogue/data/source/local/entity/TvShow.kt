package com.doniapr.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tv_shows")
@Parcelize
data class TvShow(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "originalTitle")
    var originalTitle: String? = null,

    @ColumnInfo(name = "genres")
    var genres: String? = null,

    @ColumnInfo(name = "firstAirDate")
    var firstAirDate: String? = null,

    @ColumnInfo(name = "episodeRunTime")
    var episodeRunTime: String? = null,

    @ColumnInfo(name = "inProduction")
    var inProduction: Boolean? = null,

    @ColumnInfo(name = "status")
    var status: String? = null,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: String? = null,

    @ColumnInfo(name = "voteCount")
    var voteCount: String? = null,

    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String? = null,

    @ColumnInfo(name = "numberOfEpisodes")
    var numberOfEpisodes: String? = null,

    @ColumnInfo(name = "numberOfSeasons")
    var numberOfSeasons: String? = null
) : Parcelable