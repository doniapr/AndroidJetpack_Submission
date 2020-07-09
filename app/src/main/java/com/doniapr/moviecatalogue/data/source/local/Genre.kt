package com.doniapr.moviecatalogue.data.source.local

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre (
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null
): Parcelable