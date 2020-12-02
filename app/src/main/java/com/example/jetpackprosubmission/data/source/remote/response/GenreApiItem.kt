package com.example.jetpackprosubmission.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreApiItem(
    @SerializedName("id") val id: String? = "",
    @SerializedName("name") val name: String? = null
) : Parcelable