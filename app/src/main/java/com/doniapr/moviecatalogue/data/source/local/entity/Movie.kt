package com.doniapr.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movies")
@Parcelize
data class Movie(
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

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String? = null,

    @ColumnInfo(name = "runtime")
    var runtime: String? = null,

    @ColumnInfo(name = "tagline")
    var tagline: String? = null,

    @ColumnInfo(name = "status")
    var status: String? = null,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: String? = null,

    @ColumnInfo(name = "voteCount")
    var voteCount: String? = null,

    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String? = null

) : Parcelable